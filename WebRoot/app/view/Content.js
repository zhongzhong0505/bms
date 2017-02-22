
/**
 * 主面板
 */
Ext.define("AM.view.Content",{
	extend:'Ext.tab.Panel',
	title:'欢迎使用',
	tabPosition:"bottom",
	activeTab:0,//默认打开的tab
	//deferredRender:false,//一开始就全部渲染
	alias:'widget.contentPanel',
	padding:"0 5 5 0",
	minTabWidth:1,
	items:[
	       {xtype:"startPanel",id:"start-panel"},
	       {xtype:"borrowSearchPanel",id:"borrow-search-panel"},
	      /* {xtype:"memoPanel",id:"memo-mana-panel"},*/
	       {xtype:"userPanel",id:"user-info-panel"},
	       {xtype:"bookInfoPanel",id:"book-info-panel"},
	       {xtype:"borrowHistoryPanel",id:"borrow-history-panel"},
	       {xtype:"bookStatusPanel",id:"book-status-panel"},
	       {xtype:"bookChartsPanel",id:"book-charts-panel"}
	
	],
    initComponent:function(){
    	this.callParent(arguments);
    }     
});