W.SrcCodeCol = W.Col.extend({
    header: 'Source Code',
    className: 'column double-column',
    title:null, 
    tbar: [
        {
            title: 'Save Changes',
            icon: 'fa-save',
            left: true,
            onClick: 'onSave'
        }, 
        {
            xtype: 'CloseIcon'
        }
    ],
    items:[
        {
            xtype: 'TextArea',
            alias: 'textArea'
        }
    ],
    onClose: function () {
        W.homeView.hideFromIdex(3);  
    },
    onSave: function () {
       var data = W.srcFilesCol.selectedItem.model.attributes;
       var newSrc = W.textArea.$el.val();
       
       W.srcFilesCol.selectedItem.model.set('src', newSrc);
       
       data.src = newSrc
       
       Request.post('dev/update', data);
    }
});
 