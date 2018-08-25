Ext.define('Admin.system.tools.ExcelReportButton', {
    extend: 'Ext.button.Button',
    xtype: 'excelReportButton',
    iconCls: 'x-fa fa-file-excel-o',
    tooltip: 'Excel Report',
    listeners: {
        click: function (button) {
            var grid = button.up().up().items.items[0],
                    gridTab = grid.up(),
                    grid = gridTab.items.items[0],
                    params = grid.getParams(),
                    entity = gridTab.entity,
                    idDv = gridTab.idDv,
                    appIdx = Session.CurrentAppIdx,
                    url = "/AMS/"+appIdx+ "/" + idDv+ "/"+ entity + "/excel/report?params=" + params;           
                    
            var total = grid.getStore().totalCount;
            if (total > 100) {
                if (confirm('Do you want to print a report with ' + total + ' records?')) {
                    window.open(url, '_blank');
                }
            }else{
                window.open(url , '_blank');
            }


        }
    }
});