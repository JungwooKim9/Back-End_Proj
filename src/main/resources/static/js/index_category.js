document.addEventListener('DOMContentLoaded', function(){

    var $slideCategory = document.querySelector('.ctgryprdtbox'),
        $slideBigbox = document.querySelector('.bigbox'),
        $ctgryBox = document.querySelectorAll('.ctgrysmlbox'),
        $slideCount = $ctgryBox.length,
        $currentIndex = 0,
        $navTbtn = document.getElementById('tbtn'),
        $navPbtn = document.getElementById('pbtn'),
        $navObtn = document.getElementById('obtn');

    $navTbtn.classList.add('colorchange');

    // 슬라이드 가로로 배열
    const ctgryBoxElements = document.querySelectorAll('.ctgrysmlbox');

    let leftOffset = 0;
    ctgryBoxElements.forEach((ctgryBoxElement) => {
        ctgryBoxElement.style.position = 'absolute';
        ctgryBoxElement.style.left = leftOffset + 'px';
        leftOffset += ctgryBoxElement.offsetWidth;
    });

    // 슬라이드 이동 함수
    function goToSlide(idx){
        $slideBigbox.classList.add('animated');
        $slideBigbox.style.left = -1720 * idx + 'px';
        $currentIndex = idx;
    }

    // 다음, 이전 버튼 클릭
    $navTbtn.addEventListener('click',function(){
        if($currentIndex == 1){
            goToSlide(0);
            goToSlide($currentIndex = 0);
            $navTbtn.classList.add('colorchange');
            $navPbtn.classList.remove('colorchange');
        }else if($currentIndex == 2){
            goToSlide(0);
            goToSlide($currentIndex = 0);
            $navTbtn.classList.add('colorchange');
            $navObtn.classList.remove('colorchange');
        }
    });

    $navPbtn.addEventListener('click',function(){
        if($currentIndex == 0){
            goToSlide(1);
            goToSlide($currentIndex = 1);
            $navPbtn.classList.add('colorchange');
            $navTbtn.classList.remove('colorchange');
        }else if($currentIndex == 2){
            goToSlide(1);
            goToSlide($currentIndex = 1);
            $navPbtn.classList.add('colorchange');
            $navObtn.classList.remove('colorchange');
        }
    });

    $navObtn.addEventListener('click',function(){
        if($currentIndex == 0){
            goToSlide(2);
            goToSlide($currentIndex = 2);
            $navObtn.classList.add('colorchange');
            $navTbtn.classList.remove('colorchange');
        }else if($currentIndex == 1){
            goToSlide(2);
            goToSlide($currentIndex = 2);
            $navObtn.classList.add('colorchange');
            $navPbtn.classList.remove('colorchange');
        }
    });



});