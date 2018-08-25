Ext.define('Admin.dv.view.DvDetails', {
    extend: 'Admin.system.base.BaseDetails',
    xtype: 'dvDetails',
    setData: function (data) {
        var list = this.fields;// Util.getTrFields(this.parentWidth, this.fields, data)
        var cols = (list && list[0] && list[0].items && list[0].items.length) || 50;//should never get to the 50

        if (data) {
            Ext.each(list, function (tr) {
                Ext.each(tr.items, function (field) {
                    field.val = data[field.name];
                });
            });
        }

        this.items.items[0].setData({
            items: list,
            width: 100 / cols + '%'
        });

        if (data && data.created_by && data.creation_date) {
            var text = 'Created by ' + data.created_by + ' on ' + Util.formatDate(data.creation_date, 'm/d/Y h:i:s A');

            var creationToolBar = this.getDockedItems()[0];
            var creationToolBarItem = this.getDockedItems()[0].items.items[0];

            if (!creationToolBarItem) { 
                creationToolBar.add({text: text});
            }
        }

    },
    bbar: [],
    items: [
        {
            xtype: 'component',
            flex: 1,
            data: {
            },
            tpl: new Ext.XTemplate([
                '<div style="padding:10px">',
                '<table cellpadding="10" style="width:100%;cellspacing">',
                '<col width="{width}">',
                '<tpl for="items">',
                '<tr>',
                '<tpl for="items">',
                '<td><b>{fieldLabel}: </b> {[this.formatValue(values)]}</td>',
                '</tpl>',
                '</tr>',
                '</tpl>',
                '</table>',
                '<div>'
            ].join(''),
                    {
                        formatValue: function (field) {
                            if (field && field.val) {
                                switch (field.type) {
                                    case 'Date':
                                    case 'Boolean':
                                    case 'Double':
                                        return Util['format' + field.type](field.val);
                                }
                            }
                            return field.val;
                        }
                    }
            )
        }
    ]
});

