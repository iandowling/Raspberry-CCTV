<%@include file="includes/header.jsp"%>	

	<header id="first">
        <div class="header-content">
            <div class="inner">
                <h1 class="cursive">Raspberry CCTV</h1>
                <br />
                <h4>A powerful, intuitive and affordable multipurpose security system to protect the ones you love.</h4>
                <hr></hr>
                <a href="signup" class="btn btn-primary btn-xl page-scroll">Get Started</a> &nbsp;<a href="login" id="login" class="btn btn-primary btn-xl">Login</a> 
            </div>
        </div>
        <video autoplay="autoplay" loop="loop" class="fillWidth fadeIn wow collapse in" data-wow-delay="0.5s" poster="./static/public/images/dog.gif" id="video-background">
            <source src="./static/public/images/dog.gif" type="video/mp4" ></source>
        </video>
    </header>
    
    <!-- About Section -->
    <section id="about" class="container content-section text-center">
      <div class="about-section">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <h2>About Raspberry CCTV</h2>
                <p>Raspberry CCTV is primarily a security device for entrance or exit passage ways. However, this technology could easily be deployed in many different environments and scenarios such as a baby monitoring system. Users of the system will be able to set up the Raspberry CCTV Camera over a doorway and monitor the activity of the doorway remotely on a mobile device, through the Raspberry CCTV application.</p>
                <p>The Raspberry CCTV Camera will have motion detection build in so when the system detects movement in front of the camera it will start recording video. It will also send a push notification to the mobile device so the user will be able to check who is at the doorway. Through the mobile application interface the user will be able to record images and video, along with text-to-speech functionality.</p>
                <p>This feature will allow the user to write a warning to the would be intruder or friend in the doorway and through a speaker connected to the Raspberry CCTV Camera the text will be converted to speech.</p>
                <p>For more information on how the code works visit our github page:<a href="https://github.com/iandowling/Raspberry-CCTV"> Raspberry CCTV Github <span class="fa fa-github"></span></a></p>
            </div>
        </div>
      </div>
    </section>
    
    <!-- Download Section -->
    <section id="download" class="content-section text-center">
        <div class="download-section">
            <div class="container">
                <div class="col-lg-8 col-lg-offset-2">
                    <h2>Download Raspberry CCTV on Android today <span class="fa fa-android" id="android"></span></h2>
                    <p>You can download Raspberry CCTV for free from the Google Play Store.</p>
                    <p><img src="./static/public/images/googlePlay.jpg" id="playImg"></img></p>
                    <a href="http://www.play.google.com/store/apps" class="btn btn-default btn-lg">Visit Google Play</a>
                </div>
            </div>
        </div>
    </section>
    
    <!-- Contact Section -->
    <section id="contact" class="container content-section text-center">
        <div class="row">
            <div class="col-lg-8 col-lg-offset-2">
                <h2>Contact Raspberry CCTV</h2>
                <p>Feel free to email us to provide some feedback on any issues or improvements that can be made to improve the overall performance of our product.</p>
                <p><a href="mailto:raspberrycctv@gmail.com">raspberrycctv@gmail.com</a>
                </p>
                <ul class="list-inline banner-social-buttons">
                    <li>
                        <a href="http://twitter.com/" class="btn btn-default btn-lg"><i class="fa fa-twitter fa-fw"></i> <span class="network-name">Twitter</span></a>
                    </li>
                    <li>
                        <a href="http://facebook.com/" class="btn btn-default btn-lg"><i class="fa fa-facebook fa-fw"></i> <span class="network-name">Facebook</span></a>
                    </li>
                    <li>
                        <a href="https://github.com/iandowling/Raspberry-CCTV/" class="btn btn-default btn-lg"><i class="fa fa-github fa-fw"></i> <span class="network-name">Github</span></a>
                    </li>
                    <li>
                        <a href="https://www.instagram.com/?hl=en" class="btn btn-default btn-lg"><i class="fa fa-instagram fa-fw"></i> <span class="network-name">Instagram</span></a>
                    </li>
                </ul>
            </div>
        </div>
    </section>


<%@include file="includes/footer.jsp"%>