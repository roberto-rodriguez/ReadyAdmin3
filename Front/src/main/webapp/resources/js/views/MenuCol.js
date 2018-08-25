W.MenuCol = W.Col.extend({
    header: 'Options', 
    tbar: [
        {
            title: 'Run All',
            icon: 'fa-play-circle',
            left: true,
            onclick: function () {
                alert('Run all')
            }
        } 
    ],
    items: [{
            title: 'Generate Source Code',
            handler: () => {
                if(W.entityCol){
                    W.entityCol.show();
                }else{
                    W.homeView.addItem({
                        xtype:'EntityCol'
                    })
                }
            }
        },
        {
            title: 'Setup Dev'
        },
        {
            title: 'Setup Prod'
        }
    ],

});

 