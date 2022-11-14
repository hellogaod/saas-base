
rule "${id}-${index}"
    activation-group "${id}"
    no-loop true            //只检查一次
    when
        $c : ScoreRuleModel(${strategy})
    then
    //加分
        $c.setCupoScore($c.getCupoScore()+${score}*${weight});
        System.out.println("加分2--------------"+$c.getCupoScore());
    end
