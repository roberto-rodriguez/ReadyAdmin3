Ext.define('Admin.system.field.BaseField', {
    extend: 'Ext.form.field.Text',
    xtype: 'baseField', 
    cls: 'baseStringField',
    help: null,
    listeners:{
      render: function(cmp, b, c){
          if(this.help){
               var helpIcon = Ext.DomHelper.createDom('<div class="x-fa fa-question-circle" data-qtip="' + this.help +'" style="color:grey; margin-left: 2px; font-size:15px"></div>');
               cmp.el.appendChild(helpIcon);
          } 
      }  
    },
    getKeyValue: function () {
        return {
            name: this.name,
            value: this.getValue()
        };
    }
});