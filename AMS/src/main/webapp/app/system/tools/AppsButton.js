Ext.define('Admin.system.tools.AppsButton', {
    extend: 'Ext.panel.Panel',
    border: false,
    xtype: 'appsButton',
    reference: 'appsButton',
    cls: 'sencha-logo',
    appIdx: null,
    theHtml: '<div class="main-logo"><img src="resources/images/logo.png" class="main-logo-image" style="top:14px"></div>',
    width: 250,
    tbar: [
        '->',
        {
            xtype: 'viewsButton',
            style: {
                'margin-top': '22px'
            }
        },
        '->'],
    style: {
        'background-color': 'white',
        'border-right': '1px solid gray',
        cursor: 'pointer'
    },
    listeners: {
        element: 'el',
        click: function () {
            if (Session.CurrentAppIdx == null || Session.CurrentAppIdx == C.ROOT) {
                Ext.GlobalEvents.fireEvent('selectApp', '');
            }
        }
    },
    resize: function (collapsing) {
        var me = this,
                dom = me.el.dom,
                tbar = me.getDockedItems()[0],
                image = document.getElementsByClassName('main-logo-image')[0] || {};
        this.html = null;


        if (collapsing) {
            image.src = "resources/images/icon.png";
            image.style = "width:55px; margin-left:4px;top:12px;";

            tbar.items.items[1].setText('');
            tbar.items.items[0].destroy();
        } else {
            image.src = "resources/images/logo.png";
            image.style = "top: 14px";

            tbar.items.items[0].setText('Demo');
            tbar.insert(0, '->');
        }
    },
    refresh() {
        if (Session.CurrentAppIdx == null || Session.CurrentAppIdx != this.appIdx) {
            this.appIdx = Session.CurrentAppIdx;

            if (Session.CurrentAppIdx == null || Session.CurrentAppIdx == C.ROOT) {
                this.goHome();
            } else {
                this.selectApps();
            }
        }
    },
    selectApps: function () {
        this.setHtml('');
        this.down('viewsButton').show();
    },
    goHome: function () {
        this.setHtml(this.theHtml);
        this.down('viewsButton').hide();
    }
}
);
 