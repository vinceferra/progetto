$(document).ready(function(){
            var $searchbar = $("#searchbar");
            var $risultati = $(".risultati");

            $searchbar.keyup(function(){
                var query = $searchbar.val();
                if(query.trim() !== ""){
                    $.get(contextPath + "/RicercaProdotto", {"query": query}, function(data){
                        if(data && data.length > 0){
                            $risultati.empty();
                            $risultati.show();
                            $.each(data, function(i, item){
                                var itemDiv = $("<div id='item-r' class='item" + i + "'><img id='pic' width='65' height='65' src='" + item.immagine + "'/><p id='name'>" + item.nome + "</p></div>");
                                itemDiv.on('click', function(){
                                	window.location = contextPath + "/Dettagli?id=" + item.idProdotto;
                                });
                                $risultati.append(itemDiv);
                            });
                        }
                    }).fail(function(){
                        console.error("Errore durante la ricerca del prodotto.");
                    });
                } else {
                    $risultati.hide();
                }
            });
        });