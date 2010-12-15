<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="stock.heading"/></title>
    <meta name="heading" content="<fmt:message key='stock.heading'/>"/>
    <meta name="menu" content="MainMenu"/>
</head>

<p><fmt:message key="stock.message"/></p>

<div class="separator"></div>

<ul class="glassList">
    <li>
        <a href="<c:url value='/newQuote.html'/>"><fmt:message key="stock.new.quote"/></a>
    </li>
    <li>
        <a href="<c:url value='/stockQuotesInDB.html'/>"><fmt:message key="stock.show.db.stocks"/></a>
    </li>
</ul>
