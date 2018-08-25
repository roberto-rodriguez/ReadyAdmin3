W.Section = W.Container.extend({ 
    className: 'section',
    leftIcon: null,
    tbar:[{
            title: 'Run All',
            icon: this.leftIcon,
            color:'green',
            left: true,
            onclick: this.onSave 
        },
         {
            title: 'Minimize',
            icon: 'fa-window-minimize',
            onclick: function () {
                alert('minimize')
            }
        }] 
});
 