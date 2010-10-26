$(document).ready(function(){

  $('#tagsInput').keyup(function(event) {
    if (event.keyCode == 13) {
      event.preventDefault();
      var string = $('#tagsInput').val();
      if (string.trim() != "") {
        $('#tagsInput').val("");
        var newElem = $("<li>" + string.trim() +  "&nbsp;<a href='javascript:();'>&times;</a></li>");
        $('a', newElem).click(function() {
          newElem.hide("slow", function() {
            $(this).remove();
          });
        });
        $("#showedTags").append(newElem);
      }
      return false;
    }
  });

  $("#submitButton").click(function() {
    var xmlString = '<?xml version=\"1.0\" encoding=\"UTF-8\"?> \
        <question> \
          <title>' + $('#title').val() + '</title>\
          <answer>' + $('#answer').text() + '</answer> \
          <topic>' + $('#topic').val() + '</topic> \
          <tags>';
    var li = $("#showedTags").find('li');
    for (var i = 0; i < li.length; i++) {
      var liElem = li[i];
      var tag = liElem.textContent;
      xmlString += '<tag>' + tag.slice(0, tag.length - 2) + '</tag>';
    }
    xmlString += '</tags>';
    xmlString += '</question>';
    $.ajax({
      type: "post",
      url: $("#question_edit").attr("action"),
      data: xmlString,
      processData: false,
      async: false,
      contentType: "text/xml",
      dataType: "xml"
    });
    window.location.replace("/admin/index.html")
  });
});

function addTagElement(value){
  var newElem = $("<li>" + value +  "&nbsp;<a href='javascript:();'>&times;</a></li>");
  $('a', newElem).click(function() {
    newElem.hide("slow", function() {
      $(this).remove();
    });
  });
  $("#showedTags").append(newElem);
}