/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
Ext.define('Admin.system.util.Session', {
    alternateClassName: 'Session',
    statics: {
        Principal: null,
        Apps: {},
        CurrentApp: {},
        CurrentAppIdx: null,
        CurrentEntityIdx: null,
        CurrentRoute: '',
        AppIdxList: null,
        getApp: function (idx) {
            var app = this.Apps[idx];

            if (app) {
                this.CurrentApp = app;
                this.CurrentAppIdx = app.idx;
            } else {
                this.CurrentAppIdx = null;
            }
            return app;
        },
        setApp: function (app) {
            this.Apps[app.idx] = app;
            return this.getApp(app.idx);
        },
        setMainApp: function () {
            this.CurrentApp = null;
            this.CurrentAppIdx = '/';
        },
        getUserApp: function (idx) {
            return this.Principal && this.Principal.apps
                    && Ext.Array.filter(this.Principal.apps, function (app) {
                        return app.idx === idx;
                    });
        },
        isInMainApp: function () {
            return this.CurrentAppIdx === '/';
        },
        reset: function () {
            this.Principal = null;
            this.Apps = {};
            this.CurrentApp = {};
            this.CurrentAppIdx = null;
            this.CurrentEntityIdx = null;
            this.CurrentRoute = null;
        },
        getEntity: function (idDv) {
            var CurrentApp = this.CurrentApp;
            
            return CurrentApp && CurrentApp.entities && Ext.Array.filter(CurrentApp.entities, function (e) {
                return e.id === idDv
            })[0];
        },
        getAppIdxList: function () {
            if (!this.AppIdxList) {
                this.AppIdxList = this.Principal && this.Principal.apps && Ext.Array.map(this.Principal.apps, function (app) {
                    return app.idx;
                })
            }
            return this.AppIdxList || [];
        }
    }
});

