Ext.define("AM.view.BookStatusPanel",{
	extend:'Ext.window.Window',
	 title: '图书状态统计',
     layout: 'fit',
     closeAction:'hide',
    // maximizable: true,
     width:700,
     height:450,
     store: "BookStatusStore",
     alias:'widget.bookStatusPanel',
     modal:true,
     items: {
         xtype: 'chart',
         id: 'chartCmp',
         animate: true,
         store: "BookStatusStore",
         shadow: true,
         style: 'background:#fff',
         legend: {
             position: 'right'
         },
        
         insetPadding: 60,
        // theme: 'Base:gradients',
         series: [{
             type: 'pie',
             field: 'data',
             showInLegend: true,
             donut: false,//内环状线圈
             tips: {//提示
               trackMouse: true,
               width: 100,
               height: 50,
               renderer: function(storeItem, item) {
                 var total = 0;
                 
                 storeItem.store.each(function(rec) {
                     total += rec.get('data');
                 });
                 this.setTitle(storeItem.get('name') + ': '
                 	+ storeItem.get('data')+"本 "+ "<br>比例:"
                 	+ (storeItem.get('data')/total*100+"").substring(0,4)
                 	+ '%');
               }
             },
             highlight: {//高亮
               segment: {
                 margin: 20
               }
             },
             label: {
                 field: 'name',
                 display: 'rotate',
                 contrast: true,
                 font: '18px Arial'
             }
         }]
     }
});