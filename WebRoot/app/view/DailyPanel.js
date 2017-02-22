/**
 * 日常操作
 */
Ext.define("AM.view.DailyPanel",{
	extend:'Ext.tree.Panel',
	title:'日常操作',
	alias:'widget.dailyPanel',
	iconCls:"pencil_go",
	border:false,
    store:'DailyStore',
    rootVisible:false,
    initComponent:function(){
    	this.callParent(arguments);
    }     
});