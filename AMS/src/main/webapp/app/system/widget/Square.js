Ext.define('Admin.system.widget.Square', {
    extend: 'Ext.button.Button',
    xtype: 'square',
    cls: 'square',
    text: '', 
    colors: ['#ff3366', "#0074D9", '#0000ff', '#629aa9', "#9acd32", "#fc6c85", '#008000', '#00ff00', '#800000', '#000080', '#808000', '#800080', '#ff0000', "#dc143c", '#008080', '#ffa500', '#00008b', '#00008b', '#4682b4', '#8a2be2', '#f40612'],
    width: 80,
    height: 80,
    label: null, 
    border: false,
    icon: null,
    color: null,
    initComponent: function () {
        this.style = {
            margin: '10px', 
            'border-radius': '15px',
            'font-size': '34px'
        } 
        
        if(this.color){
            this.style['background-color'] = this.color;
        }else{
             this.style['background-image'] = this.getGradient();
        }
        
        this.iconCls = 'x-fa ' + this.icon;
        this.callParent(arguments);
    },
    getGradient: function () {
        var me = this,
                name = me.label;

        if (name && name.length) {
            var baseColor = this.colors[ this.sumChars(name) % this.colors.length ];
            var ligthenColor = Util.lightenDarkenColor(baseColor, 55);
            return 'linear-gradient(' + baseColor + ', ' + ligthenColor + ');'
        }

        return null;
    },
    sumChars: function (str) {
        let sum = 0;
        for (let i = 0; i < str.length; i++) {
            sum += str.charCodeAt(i);
        }
        return sum;
    }
});
 