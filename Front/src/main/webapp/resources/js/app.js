var W = {};

W.Router = Backbone.Router.extend({

    routes: {"": "home"},

    initialize: function () {
        W.shellView = new W.ShellView();
        $('body').append(W.shellView.render().el);
        this.$content = $("#content");
    },

    home: function () {
        // Since the home view never changes, we instantiate it and render it only once
        if (!W.homeView) {
            W.homeView = new W.HomeView();
            W.homeView.render();
        } else {
            console.log('reusing home view');
            W.homeView.delegateEvents(); // delegate events when the view is recycled
        }
        this.$content.html(W.homeView.el);
    }
});

$(document).on("ready", function () {
    _.templateSettings = {
        interpolate: /\<\@\=(.+?)\@\>/gim,
        evaluate: /\<\@(.+?)\@\>/gim
    };

    Backform.bootstrap2();

    String.prototype.capitalize =  function () {
        return this.charAt(0).toUpperCase() + this.substr(1);
    }

    W.router = new W.Router();
    Backbone.history.start();
}); 