<#-- @ftlvariable name="subject" type="org.softwire.training.models.UserSummary" -->
<#-- @ftlvariable name="socialEvents" type="java.util.List<org.softwire.training.models.SocialEvent>" -->

<!DOCTYPE html>
<html lang="en">
<#include "Head.ftl">
<body>
<#include "Titlebar.ftl">


<div class="ui main text container">
    <h1 class="ui header">${subject.fullname}'s wall</h1>

    <div class="ui list divided">
        <#list socialEvents as socialEvent>
        <div class="item">
            <img class="ui avatar image" src="/assets/images/ic_note_black_24px.svg">
            <div class="content">
                <a class="header" href=/wall/${socialEvent.author.username}>${socialEvent.author.fullname}</a>
                <div class="description">${socialEvent.content}</div>
            </div>
        </div>
        </#list>
    </div>

    <form name="form" action="/wall/${subject.username}/write" method="post">
        <div class="ui action input">
            <input name="message"
                   type="text"
                   placeholder="Hey, what's up?"
                   required="required"/>
            <input class="ui button" type="submit" value="Post"/>
        </div>
    </form>

</div>

</body>
</html>