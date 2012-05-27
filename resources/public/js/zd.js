function ready_scorer() {
  $("#playerlist")
    .accordion({header: "> li > div[class=\"playerline\"]", collapsible: true, active: false})
    .sortable({axis: "y"});
};
