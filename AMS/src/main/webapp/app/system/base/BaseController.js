Ext.define('Admin.system.base.BaseController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.baseController',
    singleClickTask: null,
    executingDblClick: false,
    init: function () {
        var me = this,
                grid = me.getView().down('basePaginatedGrid');

        if (grid) {
            grid.getStore().load(); 
        }
    },
//Cuando al Grid que esta en el SuperTab se le da click
//Se actualiza el entityId del super tab y del Tab que este seleccionado en el SubPanel
//y se recarga el grid el Tab seleccionado en el SubPanel
    onGridItemClick: function (view, td, cellIndex, record) {
        var me = this,
                superTab = view.up().up(); //the tab that wraps the grid

        me.singleClickTask = setTimeout(function () { //to diferenciate between click and double click

            if (me.executingDblClick || superTab.isShowingOnBottom()) {
                me.executingDblClick = false;
                return;
            }

            if (!superTab.isShowingOnBottom()) {//Actualizar superTab.entityId solo cuando el click sea en el grid de arriba.
                superTab.entityId = record.data.id;  //Important (don't remove)
                superTab.superData = record.data;
            }

            if (superTab.items.items && //if the parent tab of the grid contains items
                    superTab.items.items.length > 1) { //if more than one
                var subPanel = view.up().up().items.items[1];

                var activeSubTab = subPanel.getActiveTab();

                if (activeSubTab) {
                    activeSubTab.filters = superTab.filters;
                    activeSubTab.entityId = superTab.entityId;

                    var activeTabElement = activeSubTab.items.items[0];

                    if (activeTabElement instanceof Admin.system.base.BasePaginatedGrid) {
                        activeTabElement.getStore().loadPage(1);
                    } else {
                        activeSubTab.load && activeSubTab.load(record.data.id, record.data);
                    }
                }

                if (!subPanel.isVisible()) {
                    subPanel.show();
                }

            }
        }, 200);
    },
    onGridItemDblClick: function (data, grid) {
        var me = this,
                tab = grid.up();

        me.executingDblClick = true;
        clearTimeout(me.singleClickTask); //to diferenciate between click and double click

        if (!tab.isShowingOnBottom()) {//Actualizar superTab.entityId solo cuando el click sea en el grid de arriba.
            tab.entityId = data.id;  //Important (don't remove)
            tab.superData = data;
        }

        me.openViewTab(data, grid);
    },
    //Usado por los botones de 'New' y 'Edit' del Toolbar
    editGridRow: function (cmp) {
        var me = this,
                grid = cmp.up().up().items.items[0];

        var data = null;
        if (cmp instanceof Admin.system.tools.EditGridRowButton) {
            data = grid.getSelectionModel().getSelection();
            //Si es el boton Edit, tomar la data del row seleccionado en el Grid
            if (data.length !== 1) {
                return;
            } else {
                data = data[0].data;
            }
        }

        me.openViewTab(data, grid);
    },
    //Cuando se le da doble click a un grid row o en la opcion 'Crear Nuevo' o la opcion de 'Editar'
    //Se crea un ViewTab del tipo de elemento del grid
    openViewTab: function (data, grid) {
        var me = this,
                xtype = grid.xtype,
                currentTab = grid.up(),
                originCmpId = grid.id,
                superTab = me.getView().getActiveTab(),
                entity = xtype.substring(0, xtype.indexOf('Grid')),
                viewTab = grid.openViewTab || entity + 'ViewTab',
                itemId = viewTab + '-' + (data ? data.id : (new Date()).getTime()), //if new, give random id
                existentTab = me.getView().down('#' + itemId),
                showingOnTop = !currentTab.isShowingOnBottom();

        if (data && showingOnTop) {
            superTab.entityId = data.id; //Important (don't remove) 
        }

        if (existentTab) {
            me.getView().setActiveTab(existentTab);
        } else {

            var newTab = {
                xtype: viewTab,
                closable: true,
                id: itemId,
                idDv: currentTab.idDv || 0, //For dv, represents the id of the entity
                viewName: currentTab.title,
                entity: currentTab.entity,
                itemId: itemId,
                filters: entity + '.id',
                //'filters' determina el filtro que se le aplicara a los Sub Tabs
                //de la vista que se esta abriendo
                //OJO
                //Debe ser especificado en el SubPanel que contiene el GridTab (ver PartnerSubPanel como ejemplo)
                //y nunca ser especificado en el mismo GridTab, ya que ese GridTab puede ser utilizado
                //desde varios SubPanels con diferentes filtros. 
                entityId: (data ? data.id : null),
                superEntityId: superTab.entityId,
                //En algunos casos se necesita el ID de la entidad seleccionada en el SuperTab
                originCmpId: originCmpId,
                //ID del componente desde el cual se esta abriendo una nueva vista
                //Esto se usa para refrescar dicho componente despues de crear/editar 
                data: data,
                superData: (showingOnTop && !data) ? null : superTab.superData,
                subPanelsToShow: currentTab.subPanelsToShow,
                //Usar cuando se quiera que el tab que se abre, muestre solo ciertos subpanels
                fields: currentTab.fields, //Will be used by DV to generate views dynamically
                iconCls: currentTab.iconCls
            };

            if (grid.loadAdditionalInfoWhenDblClick && data) {
                Request.load({
                    url: data.id + '/' + currentTab.entity + '/load', //For load, the id is data.id
                    params: 'params=id@is@(I)' + data.id,
                    success: function (response) {
                        var data = response.data;
                        newTab.data = data;

                        if (showingOnTop) {
                            newTab.superData = data; //If is tab on top, superData should include the data obtained on load
                        }

                        me.getView().setActiveTab(newTab);
                        me.getView().down('#' + itemId).init(data);
                    }
                });
            } else {
                me.getView().setActiveTab(newTab);
                me.getView().down('#' + itemId).init(data);
            }
        }
    },
    deleteGridRow: function (cmp) {
        var me = this,
                grid = cmp.up().up().items.items[0],
                subpanel = cmp.up().up().items.items[1],
                currentTab = grid.up(),
                message = "Are you sure you want to delete?",
                data = grid.getSelectionModel().getSelection(),
                idDv = currentTab.idDv || 0;

        if (data.length !== 1) {//si no ha selecciondo nada
            return;
        } else {
            data = data[0].data;
        }

        Ext.Msg.confirm("Delete", message, function (btn) {
            if (btn == "yes") {
                Request.load({
                    url: idDv + '/' + currentTab.entity + '/delete',
                    method: 'POST',
                    mask: 'Removing...',
                    jsonData: '{"id":"(I)' + data.id + '"}',
                    ignoreError: true,
                    success: function (response) {
                        if (response.status === 500) {
                            Ext.Msg.alert('Information', response.statusMessage);
                        } else {
                            grid.getStore().loadPage(1);
                            if (subpanel && subpanel instanceof Admin.system.base.BaseSubPanel) {
                                var activeSubTab = subpanel.getActiveTab();

                                if (activeSubTab) {
                                    var activeTabElement = activeSubTab.items.items[0];

                                    if (activeTabElement instanceof Admin.system.base.BasePaginatedGrid) {
                                        activeTabElement.getStore().loadPage(1);
                                    }
                                }
                            }


                        }
                    }
                })
            }
        });
    }
});
