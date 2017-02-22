
var index = 0;
Ext.define("AM.view.BookChartsPanel",{
	extend:'Ext.window.Window',
	width: 800,
    height: 450,
    hidden: false,
    closeAction:'hide',
    //maximizable: true,
    title: '图书类别统计',
    layout: "border",
    alias:'widget.bookChartsPanel',
    modal:true,
    items: [{
        id: 'chartCmp',
        xtype: 'chart',
        style: 'background:#fff',
        animate: true,
        region:"center",
        shadow: true,
        store: "BookChartsStore",
        axes: [{//轴
            type: 'Numeric',
            position: 'left',
            
            majorTickSteps:5,
            minorTickSteps :5,
            minimum:0,
            maximum:200,
           // fields: 'data1,data2,data3,data4,data5,data6',
            title: '数量（本）',
            grid: true,
            minimum: 0
        }, {
            type: 'Category',
            position: 'bottom',
            fields: 'name',
            title: '图书类别'
        }],//序列
        series: [{
            type: 'column',
            axis: 'left',
            highlight: true,
            tips: {//提示
              trackMouse: true,
              width: 140,
              height: 28,
              renderer: function(storeItem,item) {
                	this.setTitle(storeItem.get('name') + ': ' 
                	+ storeItem.get('data') + ' 本');
              }
            },
            //格式化
            renderer: function(sprite, record, attr, index, store){
                  
            		index = index + 1;
            		if(index==6){
            			index = 0;
            		}
                    var color = ['rgb(213, 70, 121)', 
                                 'rgb(44, 153, 201)', 
                                 'rgb(146, 6, 157)', 
                                 'rgb(49, 149, 0)', 
                                 'rgb(249, 153, 0)'][index];
                    return Ext.apply(attr, {
                        fill: color
                    });
            } ,                 
            label: { //控制柱形图label
              	display: 'insideEnd',
              	'text-anchor': 'middle',
                field: 'data',
                renderer: Ext.util.Format.numberRenderer('0'),
                orientation: 'vertical',
                color: '#fff'
            },
            xField: 'name',
            yField: 'data'
        }]
    }]
});