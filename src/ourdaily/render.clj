(ns ourdaily.render
  (:use [hiccup.core]))

(def msgs [[{:date-recieved "Thu Jul 09 01:00:04 BRT 2015", :date-sent "Thu Jul 09 01:00:04 BRT 2015", :project {:name "ourdaily", :title "Primeiro Projeto"}, :from {:email "lucas@stereocause.com", :text "Lucas Reis <lucas@stereocause.com>"}, :content-type "multipart/ALTERNATIVE; boundary=001a1135b6f24ec08c051a694a7a", :contents {:did "2 alguma coisa", :willdo "outra coisa 2", :imp "ediments\r\n\r\n2 nenhum!"}, :multipart? true, :body ({:content-type "TEXT/PLAIN; charset=UTF-8", :body "oi, tudo bem?\r\n\r\n#did\r\n\r\n2 alguma coisa\r\n\r\n#willdo\r\n\r\noutra coisa 2\r\n\r\n#impediments\r\n\r\n2 nenhum!\r\n\r\n#end\r\n\r\n*Lucas Reis*\r\nStereo Cause\r\n+55 21 97111 2373\r\n"} {:content-type "TEXT/HTML; charset=UTF-8", :body "<div dir=\"ltr\"><span style=\"font-size:12.8000001907349px\">oi, tudo bem?</span><div style=\"font-size:12.8000001907349px\"><br></div><div style=\"font-size:12.8000001907349px\">#did</div><div style=\"font-size:12.8000001907349px\"><br></div><div style=\"font-size:12.8000001907349px\">2 alguma coisa</div><div style=\"font-size:12.8000001907349px\"><br></div><div style=\"font-size:12.8000001907349px\">#willdo</div><div style=\"font-size:12.8000001907349px\"><br></div><div style=\"font-size:12.8000001907349px\">outra coisa 2</div><div style=\"font-size:12.8000001907349px\"><br></div><div style=\"font-size:12.8000001907349px\">#impediments</div><div style=\"font-size:12.8000001907349px\"><br></div><div style=\"font-size:12.8000001907349px\">2 nenhum!</div><div style=\"font-size:12.8000001907349px\"><br></div><div style=\"font-size:12.8000001907349px\">#end</div><div style=\"font-size:12.8000001907349px\"><br></div><div><div class=\"gmail_signature\"><div dir=\"ltr\"><b><font color=\"#666666\">Lucas Reis</font></b><div><font color=\"#666666\">Stereo Cause<br></font><div><font color=\"#666666\">+55 21 97111 2373</font><br><div><br></div></div></div></div></div></div>\r\n</div>\r\n"}), :user {:email "lucas@stereocause.com", :name "Lucas SC"}, :subject "#ourdaily", :to "ourdailymeeting@gmail.com"} {:date-recieved "Thu Jul 09 00:59:33 BRT 2015", :date-sent "Thu Jul 09 00:59:32 BRT 2015", :project {:name "ourdaily", :title "Primeiro Projeto"}, :from {:email "lucasmreis@gmail.com", :text "Lucas Reis <lucasmreis@gmail.com>"}, :content-type "multipart/ALTERNATIVE; boundary=089e01538c5c6ab292051a69483d", :contents {:did "alguma coisa", :willdo "outra coisa", :imp "ediments\r\n\r\nnenhum!"}, :multipart? true, :body ({:content-type "TEXT/PLAIN; charset=UTF-8", :body "oi, tudo bem?\r\n\r\n#did\r\n\r\nalguma coisa\r\n\r\n#willdo\r\n\r\noutra coisa\r\n\r\n#impediments\r\n\r\nnenhum!\r\n\r\n#end\r\n"} {:content-type "TEXT/HTML; charset=UTF-8", :body "<div dir=\"ltr\"><span style=\"font-size:12.8000001907349px\">oi, tudo bem?</span><div style=\"font-size:12.8000001907349px\"><br></div><div style=\"font-size:12.8000001907349px\">#did</div><div style=\"font-size:12.8000001907349px\"><br></div><div style=\"font-size:12.8000001907349px\">alguma coisa</div><div style=\"font-size:12.8000001907349px\"><br></div><div style=\"font-size:12.8000001907349px\">#willdo</div><div style=\"font-size:12.8000001907349px\"><br></div><div style=\"font-size:12.8000001907349px\">outra coisa</div><div style=\"font-size:12.8000001907349px\"><br></div><div style=\"font-size:12.8000001907349px\">#impediments</div><div style=\"font-size:12.8000001907349px\"><br></div><div style=\"font-size:12.8000001907349px\">nenhum!</div><div style=\"font-size:12.8000001907349px\"><br></div><div style=\"font-size:12.8000001907349px\">#end</div></div>\r\n"}), :user {:email "lucasmreis@gmail.com", :name "Lucas Reis"}, :subject "#ourdaily", :to "ourdailymeeting@gmail.com"}]])

(defn msg-component [m]
  (let [u (:user m)
        c (:contents m)]
    [:div.message
     [:h3 (:name u)]
     [:h4 "Did:"]
     [:p (:did c)]
     [:h4 "Will do:"]
     [:p (:willdo c)]
     [:h4 "Impediments:"]
     [:p (:imp c)]]))

(defn digest [d]
  (let [p (:project (first d))]
    [:div.project
     [:h2 (:title p)]
     (map msg-component d)]))

(defn render-digest [d]
  (html (digest d)))

(render-digest (first msgs))





