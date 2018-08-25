Ext.define('Admin.system.widget.Avatar', {
    extend: 'Ext.Toolbar.TextItem',
    xtype: 'avatar',
    cls: 'avatar',
    text: '',
    height: 35,
    width: 35,
    label: null,
    colors: ['#ff3366', "#0074D9", 'blue', '#629aa9', "#9acd32", "#fc6c85", 'green', 'lime', 'maroon', 'navy', 'olive', 'purple', 'red', "crimson", 'teal', 'orange', 'darkblue', 'steelblue', 'blueviolet', '#f40612'],
    update: function (name) {
        var dom = this.el.dom;

        if (name && name.length) {
            var initial = name.charAt(0);
            dom.innerHTML = initial && initial.toUpperCase();
            dom.style['background-color'] = this.colors[ this.sumChars(name) % this.colors.length ]
        }
    },
    listeners: {
        render: function (a, b, c) {
            this.update(this.label); 
        }
    },
    sumChars: function (str) {
        let sum = 0;
        for (let i = 0; i < str.length; i++) {
            sum += str.charCodeAt(i);
        }
        return sum;
    }
});
 