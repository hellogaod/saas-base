package cn.fintecher.pangolin.business.web;
import cn.fintecher.pangolin.business.model.CaseInfoDistributedModel
global java.util.List checkedList
dialect  "mvel"

rule "${id}"
no-loop true            //只检查一次
dialect "mvel"
when
$c : CaseInfoDistributedModel(${strategyText})
then
checkedList.add($c);
end