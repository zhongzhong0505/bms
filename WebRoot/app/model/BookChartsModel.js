
/**
 * 图书类别统计
 */
Ext.define("AM.model.BookChartsModel",{
	extend:'Ext.data.Model',
	fields:[
		{name:'name',type:"string",sortable:true},//图书类别
		{name:'data',type:"int",sortable:true}//图书数量
		]
});