var headerText = `
<!-- ****** Footer Menu Area Start ****** -->
<footer class="footer_area">
    <div class="container">
        <div class="row">
            <div class="col-12">
                <div class="footer-content">
                    <!-- Menu Area Start -->
                    <div class="row">
                        <div class="col-12">
                            <div class="footer-social-area d-flex">
                                <div class="footer-logo-area text-center">
                                    <p class="yummy-logo">WannaGo</p>
                                </div>
                                <div class="footer-privacy"><a>© 2022</a>
                                        <a>WannaGo</a>
                                        <a>Все права защищены</a>
                                </div>
                                <div class="footer-link">
                                    <a class="nav-link-footer" href="index.html">Главная</a>
                                </div>
                                <div class="footer-link">
                                    <a class="nav-link-footer" href="./tour-list.html">Туры</a>
                                </div>
                                <div class="footer-link">
                                    <a class="nav-link-footer" href="archive.html">Личный кабинет</a>
                                </div>
                                <div class="footer-contacts"><a>Наши контакты</a>

                                    <div>Email: wannago@gmail.com
                                        Телефон: + 7 920 885 02 64
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>

<!-- ****** Footer Menu Area End ****** -->`;

function setHeader() {
  var header =  document.createElement("div");
  header.innerHTML = headerText ;
  document.body.insertAdjacentElement('beforeend', header );
}

setHeader();