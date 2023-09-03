document.addEventListener('DOMContentLoaded', function(){

    var $slideWrap = document.querySelector('.container'),
        $slideBanner = document.querySelector('.banner'),
        $inner = document.querySelectorAll('inner'),
        $slideCount = $inner.length,
        $currentIndex = 0,
        $navLbtn = document.getElementById('lbtn'),
        $navRbtn = document.getElementById('rbtn');

    // 슬라이드 가로로 배열
    const innerElements = document.querySelectorAll('.inner');

    let leftOffset = 0;
    innerElements.forEach((innerElement) => {
        innerElement.style.position = 'absolute';
        innerElement.style.left = leftOffset + 'px';
        leftOffset += innerElement.offsetWidth;
    });

    // 슬라이드 이동 함수
    function goToSlide(idx){
        $slideBanner.classList.add('animated');
        $slideBanner.style.left = -1500 * idx + 'px';
        $currentIndex = idx;
    }

    // 다음, 이전 버튼 클릭
    $navLbtn.addEventListener('click',function(){
        if($currentIndex == 0){
            goToSlide(2);
        }else{
            goToSlide($currentIndex - 1);
        }
    });
    $navRbtn.addEventListener('click',function(){

        if($currentIndex == 2){
            goToSlide(0);
        }else{
            goToSlide($currentIndex + 1);
        }
    });

});