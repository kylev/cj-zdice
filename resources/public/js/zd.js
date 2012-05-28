function scorer_ready() {
  $("#playerlist")
    .accordion({header: "> li > div[class=\"playerline\"]", collapsible: true, active: false, icons: false, disabled: true})
    .sortable({axis: "y"});

  $("button#start").button()
    .click(scoreer_start);

  $("button.nextplayer")
    .button()
    .click(scorer_nextplayer);
  $("button.score-plus")
    .button({icons: {primary: 'ui-icon-plus'}, text: false});
  $("button.score-minus")
    .button({icons: {primary: 'ui-icon-minus'}, text: false});
};

function scoreer_start() {
  $("#playerlist").accordion("activate", 0);
};

function scorer_nextplayer(event) {
  // alert(event.target);
  // $("#playerlist").accordion("next");
};
