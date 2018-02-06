<#-- @ftlvariable name="loggedInUser" type="String" -->

<div class="ui fixed inverted menu">
    <div class="ui container">
        <a class="header item" href="/">
            MyFace
        </a>
        <a class="header item right" href="/wall/${loggedInUser}">
            <img class="ui avatar image" src="/assets/images/ic_face_black_24px.svg">
            ${loggedInUser}
        </a>
    </div>
</div>
