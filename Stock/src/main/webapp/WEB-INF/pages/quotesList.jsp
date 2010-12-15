<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="stock.heading"/></title>
    <meta name="heading" content="<fmt:message key='stock.list.heading'/>"/>
    <meta name="menu" content="MainMenu"/>
</head>





<display:table name="quotesList" cellspacing="0" cellpadding="0" requestURI="" 
    defaultsort="1" id="quotes" pagesize="25" class="table" export="true">
    <display:column property="stockTicker" escapeXml="true" sortable="true" titleKey="stock.ticker" style="width: 25%"/>
    <display:column property="quote" escapeXml="true" sortable="true" titleKey="stock.quote" style="width: 25%"/>
    <display:column property="createdDate" sortable="true" titleKey="stock.created.date" style="width: 25%"/>
    <display:column property="lastUpdatedDate" titleKey="stock.last.updated.date" />    
</display:table>


<script type="text/javascript">
    highlightTableRows("quotes");
</script>
