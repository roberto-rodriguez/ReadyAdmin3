
Request = {
    HOST: /Front/,
    post: function (url, data, callback) {
        $.ajax({
            url: Request.HOST + url,
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: callback
        })
    },
    get: function (url,callback) {
        $.get(Request.HOST + url, null, callback);
    }
}