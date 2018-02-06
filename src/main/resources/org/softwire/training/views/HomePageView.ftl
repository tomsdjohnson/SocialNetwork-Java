<#-- @ftlvariable name="users" type="java.util.List<org.softwire.training.models.UserSummary>" -->

<!DOCTYPE html>
<html lang="en">
<#include "Head.ftl">
<body>
<#include "Titlebar.ftl">

<div class="ui main text container">
    <h1 class="ui header">MyFace</h1>

    The brand new social network.

    <h4 class="ui horizontal header">Users</h4>

    <div class="ui list divided">
        <#list users as user>
            <div class="item">
                <img class="ui avatar image" src="assets/images/ic_face_black_24px.svg">
                <div class="content">
                    <a class="header" href=/wall/${user.username}>${user.fullname}</a>
                </div>
            </div>
        </#list>
    </div>
</div>

</body>
</html>