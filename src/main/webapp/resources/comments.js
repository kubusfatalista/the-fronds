
	function likeStatus(statusId) {
		var rootUrl = location.href.split("Fronds")[0];
		$.get(rootUrl + "Fronds/comments/lajk/" + statusId);
		if ( $('#lajksAndHejtsId'+statusId).css('display') == 'none' ) {
			$('#lajksAndHejtsId'+statusId).text("Lajkujesz to");
			$('#lajksAndHejtsId'+statusId).fadeIn(400);
		}
	}
	
	function hateStatus(statusId) {
		var rootUrl = location.href.split("Fronds")[0];
		$.get(rootUrl + "Fronds/comments/hejt/" + statusId);
		if ( $('#lajksAndHejtsId'+statusId).css('display') == 'none' ) {
			$('#lajksAndHejtsId'+statusId).text("Hejtujesz to");
			$('#lajksAndHejtsId'+statusId).fadeIn(400);
		}
	}
	
	function showComments(statusId) {
		if ($('#commentsListId'+statusId).css('display') == 'none' ) {
			var rootUrl = location.href.split("Fronds")[0];
			$('#commentsListId'+statusId).load(rootUrl + "Fronds/comments/"+statusId, function() {
				$('#commentsListId'+statusId).fadeIn(400);
			});
		} else {
			$('#commentsListId'+statusId).fadeOut(400);
		}
	}
	
	
	
	