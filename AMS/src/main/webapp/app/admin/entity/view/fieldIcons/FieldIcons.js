Ext.define('Admin.admin.entity.view.fieldIcons.FieldIcons', {
    extend: 'Ext.container.Container',
    xtype: 'fieldIcons',
    style:{
      'margin-top': '10px'  
    },
    layout: {
        type: 'hbox',
        align: 'stretch'
    },
    defaults: {
        xtype: 'baseFieldIcon'
    },
    items: [
        {
            icon: 'x-fa fa-text-width',
            text: 'Text',
            type: 'String'
        },
        {
            icon: 'x-fa fa-calculator',
            text: 'Number',
            type: 'Double' 
        },
        {
            icon: 'x-fa fa-calendar-o',
            text: 'Date'  
        },
        {
            icon: 'x-fa fa-check-square-o',
            text: 'Boolean' 
        },
        {
            icon: 'x-fa fa-cube',
            text: 'Entity',
            tooltip: 'Reference to ahother Entity'
        },
        {
            icon: 'x-fa fa-list-ul ',
            text: 'List' 
        } 
    ]
});