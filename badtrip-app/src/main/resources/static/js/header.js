var headerText = `
<body>

<!-- ****** Header ****** -->
<header class="header_area">
    <div class="container" ng-controller="mainController">
        <div class="row">
            <div class="col-12">
                <div class="top_header_area">
                    <div class="container-lg">
                        <div class="row">
                            <div class="col-5 col-sm-3">
                                <!--  Top Social bar start -->
                                <div class="top_social_bar">
                                    <div class="logo_area text-center">
                                        <a href="index.html" class="yummy-logo">WannaGo</a>
                                    </div>
                                </div>
                            </div>
                            <nav class="navbar navbar-expand-lg">
                                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#yummyfood-nav" aria-controls="yummyfood-nav" aria-expanded="false" aria-label="Toggle navigation"><i class="fa fa-bars" aria-hidden="true"></i> Menu</button>
                                <!-- Menu Area Start -->
                                <div class="search">
                                    <input type="text" maxlength= "12" placeholder="Найди то, что ты давно ищешь" class="searchbar">
                                    <img src="https://images-na.ssl-images-amazon.com/images/I/41gYkruZM2L.png" alt="search icon" class="button">
                                    </div>
                                <div class="collapse navbar-collapse justify-content-center" id="yummyfood-nav">
                                    <ul class="navbar-nav" id="yummy-nav">
                                        <li class="nav-item">
                                            <a class="nav-link" href="index.html">Главная</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="./tour-list.html">Туры</a>
                                        </li>
                                    </ul>
                                </div>
                            </nav>
                            <!--  Login Register Area -->
                            <div class="col-1">
                                <div class="signup-search-area">
                                    <div class="login_register_area justify-content-end">
                                        <div class="login/Registration">
                                            <button class="btn btn-submit login_register" data-toggle="modal"  ng-show="!isUserLoggedIn()" data-target="#myModal">Войти</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
    </div>
</header>
<!-- ****** Header ****** -->

<!-- modal -->

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" 
    aria-hidden="true" ng-controller="mainController"> 
    <div class="modal-dialog"> 
        <div class="modal-content"> 
            <div class="modal-header"> 
            <div class="col-md-12"><img class="head-icon" src="img/icons/WannaGo.png"></div> 
                <!-- <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button> --> 
                <span class="close"></span>
            </div> 
            <ul class="nav nav-tabs"> 
                <li class="login"><a href="#Login" class="nav-login-link" data-toggle="tab">Вход</a></li> 
                <li class="login"><a href="#Registration" class="nav-login-link" data-toggle="tab">Регистрация</a></li> 
            </ul> 
            <div class="-modalbody" style="position: center;"> 
                <div class="row"> 
                    <div class="col-md-12"> 
                        <!-- Tab panes --> 
                        <div class="tab-content"> 
                            <div class="tab-pane active" id="Login"> 
                                <form role="form" class="form-horizontal"> 
                                <div class="form-group"> 
                                    <label for="email" class="col-sm-12 control-label"> 
                                        Вход по почте </label> 
                                    <div class="col-sm-12"> 
                                        <input type="text" class="form-control" id="email1" ng-model="user.login" placeholder="i.ivanov@example.ru" /> 
                                    </div> 
                                </div> 
                                <div class="form-group"> 
                                    <div class="col-sm-12"> 
                                        <input type="password" class="form-control" id="exampleInputPassword1" ng-model="user.password" placeholder="Пароль" /> 
                                    </div> 
                                </div> 
                                <div class="container"> 
                                <div class="row"> 
                                    <div class="col-sm-12"> 
                                        <button type="submit" class="btn blt-login btn-submit btn-lg btn-block" ng-click="tryToAuth()">Войти</button> 
                                        <div><a href="#" class="forgot">Забыли пароль?</a></div> 
                                    </div> 
                                </div> 
                            </div> 
                                </form> 
                            </div> 
                            <div class="tab-pane" id="Registration"> 
                                <form role="form" class="form-horizontal"> 
                                    <!-- <div class="form-group"> 
                                    <label for="email" class="col-sm-12 control-label">Логин:</label> 
                                    <div class="col-sm-12"> 
                                        <div class="row"> 
                                            <div class="col-md-12"> 
                                                <input type="text" class="form-control" placeholder="Логин" /> 
                                            </div> 
                                        </div> 
                                    </div> 
                                </div> --> 
                                <div class="form-group"> 
                                    <label for="email" class="col-sm-12 control-label control-label-registration"> 
                                        Почта:</label> 
                                    <div class="col-sm-12"> 
                                        <input type="email" class="form-control" id="email" placeholder="i.ivanov@example.ru" /> 
                                    </div> 
                                </div> 
                                <div class="form-group"> 
                                    <label for="password" class="col-sm-12 control-label control-label-registration">
                                    Пароль:</label> 
                                    <div class="col-sm-12"> 
                                        <input type="password" class="form-control" id="password" placeholder="*********" /> 
                                    </div> 
                                </div> 
                                <div class="form-group"> 
                                    <label for="mobile" class="col-sm-12 control-label control-label-registration"> 
                                        Повторите пароль:</label> 
                                    <div class="col-sm-12"> 
                                        <input type="password" class="form-control form-contral-lastchield" id="password" placeholder="*********" /> 
                                    </div> 
                                </div> 
                                <div class="row"> 
                                    <div class="col-sm-12"> 
                                        <div class="container"> 
                                            <button type="button" class="btn btn-submit btn-lg btn-block">Зарегистрироваться</button> 
                                        </div>
                                         
                                    </div> 
                                </div> 
                                </form> 
                            </div> 
                        </div> 
                    </div> 
                    <div class="col-md-12"> 
                        <div class="row text-center sign-with"> 
                            <div class="col-md-12"> 
                                <div class="sign-with-social">Войти через соцсети</div> 
                            </div> 
                        <div class="col-md-12">
                            <div class="d-flex flex-column bd-highlight mb-3"> 
                                <div class="flex-form-registration p-2 bd-highlight"> 
                                    <span class="icon-registration icon-registration-google"></span> 
                                    Войти через Google 
                                </div> 
                                <div class="flex-form-registration p-2 bd-highlight"> 
                                    <span class="icon-registration icon-registration-apple"></span> 
                                    Войти через Apple 
                                </div> 
                                <div class="flex-form-registration p-2 bd-highlight"> 
                                    <span class="icon-registration icon-registration-vk"></span> 
                                    Войти через Вконтакте 
                                </div> 
                                <div class="flex-form-registration p-2 bd-highlight"> 
                                    <span class="icon-registration icon-registration-gos"></span> 
                                    Войти через Гос. Услуги</div> 
                              </div> 
                            </div> 
                        </div> 
                    </div> 
                </div> 
            </div> 
        </div> 
    </div> 
</div>
<!-- modal -->`;

function setHeader() {
  var header =  document.createElement("div");
  header.innerHTML = headerText ;
  document.body.insertAdjacentElement('afterbegin', header );
}

setHeader();