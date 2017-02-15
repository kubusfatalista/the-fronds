var targetLeft  = 0;
var targetTop = 0;
var $navBar = $('#navigationBar');
var $navContainer =$('#nav-container');
var $leftColumn = $('.left-column');
var $rightColumn = $('.right-column');
$(window).scroll(function() {
	if($navContainer.css('margin-left') == '0px') {
		var left = $(window).scrollLeft();
		var adj = targetLeft - left;
		$navBar.css({left: adj});
		$leftColumn.css({left: adj});
		$rightColumn.css({left: adj});
	}
 });


$(window).resize(function() {
	if($navContainer.css('margin-left') != '0px') {
		$navBar.css({left: 'auto'});
        $leftColumn.css({left: 'auto'});
        $rightColumn.css({left: 'auto'});
	 }
});
