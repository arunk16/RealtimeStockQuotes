<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="stock.heading"/></title>
    <meta name="heading" content="<fmt:message key='stock.heading'/>"/>
    <meta name="menu" content="mainMenu"/>
</head>

<!--
    The most important part is to declare your form's enctype to be "multipart/form-data"
-->
<spring:bind path="stockPriceVO.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="/images/iconWarning.gif"/>"
                alt="<fmt:message key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<div class="separator"></div>

<form:form commandName="stockPriceVO" method="post" action="newQuote.html"  id="stockForm">
<ul>
    <li class="info">
        <fmt:message key="stock.new.quote"/>
    </li>
    <li>
        <appfuse:label key="stock.enter.ticker" styleClass="desc"/>
        <form:errors path="stockTicker" cssClass="fieldError"/>
        <form:input path="stockTicker" id="stockTicker" cssClass="text medium" cssErrorClass="text state error"/>
        <form:label path="quote" id="quote" />
    </li>
    
    <li class="buttonBar bottom">
        <input type="submit" name="submit" class="button" onclick="bCancel=false"
            value="<fmt:message key="button.submit"/>" />
        <input type="submit" name="cancel" class="button" onclick="bCancel=true"
            value="<fmt:message key="button.cancel"/>" />
    </li>
</ul>
</form:form>


<script type="text/javascript" src="<c:url value="/scripts/validator.jsp"/>"></script>
