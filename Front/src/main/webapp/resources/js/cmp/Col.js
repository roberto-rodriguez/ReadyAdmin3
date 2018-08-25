W.Col = W.Container.extend({
    className: 'column',
    tpl:'Col',
    titleCls: 'col-title',
    close: function( closeAfterMe ){ 
        for(var i = 0; i < this.parent.children.length; i++){
            var el = this.parent.children[i];
            var ind = this.index;
            
            if(closeAfterMe){
                ind++;
            }
            
            if(el.index >= ind){
                if(el.hide){
                    el.hide();
                }else{
                    el.$el.hide();
                } 
            }
        }
    }
});

 