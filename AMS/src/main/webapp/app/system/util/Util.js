Ext.define('Admin.system.util.Util', {
    alternateClassName: 'Util',
    statics: {
        substractString: function (str, part) {
            var index;
            if ((index = str.indexOf(part)) > -1) {
                return str.substring(0, index);
            }
            return str;
        },
        formatDate: function (dateTime, format) {
            if (dateTime) {
                var date = new Date(parseInt(dateTime));
                return Ext.Date.format(date, (format || 'm/d/Y'));
            }
            return dateTime;
        },
        formatDateTime: function (dateTime) {
            return Util.formatDate(dateTime, 'm/d/Y H:i:s');
        },
        formatDouble: function (amount) {
            return Ext.util.Format.number(amount, '0,000.00');
        },
        formatBoolean: function (isPlatformLevel) {
            if (isPlatformLevel) {
                return "Yes";
            }
            return "No";
        }, 
        exists: function (o) {
            return typeof o != 'undefined' && o != null && o != '';
        },
        //To use in DVs
        getTrFields: function (width, fields, data) {
            var cols = width <= 1200 ? 3 : 4;
            var index = 0;
            var arrayLength = fields.length;
            var trs = [];
            fields = fields || [];
            
            if (data) {
                for (var i = 0; i < arrayLength; i++) {
                    fields[i].val = data[fields[i].name]
                }
            }

            for (index = 0; index < arrayLength; index += cols) {
                trs.push(fields.slice(index, index + cols));
            }
            return trs;
        },
        //TODO make getTrFields to use this
        getResponsiveRows: function(items, itemWidth){
            var width = window.innerWidth - 300; //window.innerWidth - 250 - 50
            var cols = parseInt( width / itemWidth );
            
            var rows = [];
            
            for (var i = 0; i < items.length; i += cols) {
                var row = items.slice(i, i+ cols);
                rows.push( {items: row} );
            }
            
            return rows;
        }, 
        lightenDarkenColor : function(col,amt) {
          amt = amt || 20;
          var usePound = true;
          if ( col[0] == "#" ) {
              col = col.slice(1);
              usePound = true;
          }

          var num = parseInt(col,16);

          var r = (num >> 16) + amt;

          if ( r > 255 ) r = 255;
          else if  (r < 0) r = 0;

          var b = ((num >> 8) & 0x00FF) + amt;

          if ( b > 255 ) b = 255;
          else if  (b < 0) b = 0;

          var g = (num & 0x0000FF) + amt;

          if ( g > 255 ) g = 255;
          else if  ( g < 0 ) g = 0;

          return (usePound?"#":"") + (g | (b << 8) | (r << 16)).toString(16);
      },
      setCookie: function(cookie){
          document.cookie = 'RA_T0k3n=' + cookie
      }
    }
});