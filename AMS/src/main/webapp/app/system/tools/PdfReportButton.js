Ext.define('Admin.system.tools.PdfReportButton', {
    extend: 'Ext.button.Button',
    xtype: 'pdfReportButton',
    iconCls: 'x-fa fa-file-pdf-o',
    tooltip: 'PDF Report',
    listeners: {
        click: function (button) {
            var me = this,
                    grid = button.up().up().items.items[0],
                    gridTab = grid.up(),
                    params = grid.getParams(),
                    entity = gridTab.entity,
                    idDv = gridTab.idDv,
                    appIdx = Session.CurrentAppIdx,
                    url = "/AMS/"+appIdx+ "/" + idDv+ "/"+ entity + "/pdf/report?params=" + params;
        
            if (grid.getStore().totalCount > 100) {
                if (confirm('Do you want to print a report with ' + total + ' records?')) {
                  window.open(url, 'window', 'HEIGHT=660,resizable=yes,scrollbars=yes,WIDTH=800,target="blank_"');
                }
            } else {
               window.open(url, 'window', 'HEIGHT=660,resizable=yes,scrollbars=yes,WIDTH=800,target="blank_"');
            }
        } 
    }
});