//服务名称前缀
export const servicePreName = "saas-"

/**
 * 请求类型
 */
export const requestType = {
  Delete: "DELETE",
  Get: "GET",
  Post: "POST",
  Put: "PUT"
}

/**
 * 消息类型
 */
export const reminderType = {
  repayment: {
    name: '还款提醒',
    value: 'REPAYMENT',
    code: 69
  },
  followUp: {
    name: '跟进提醒',
    value: 'FLLOWUP'
  },
  repaired: {
    value: 'REPAIRED',
    name: '修复提醒'
  },
  derate: {
    value: 'DERATE',
    name: '减免审批提醒'
  },
  apply: {
    value: 'APPLY',
    name: '还款审核提醒'
  },
  assistApprove: {
    value: 'ASSIST_APPROVE',
    name: '协催审批提醒'
  },
  visitApprove: {
    value: 'VISIT_APPROVE',
    name: '外访申请审批提醒'
  },
  leaveCase: {
    value: 'LEAVE_CASE',
    name: '留案案件提醒'
  },
  circulation: {
    value: 'CIRCULATION',
    name: '提前流转审批提醒'
  },
  forceTurn: {
    value: 'FORCE_TURN',
    name: '强制流转案件提醒'
  },
  memoModify: {
    value: 'MEMO_MODIFY',
    name: '修改备注提醒'
  },
  caseExpire: {
    value: 'CASE_EXPIRE',
    name: '案件到期提醒'
  },
  verification: {
    value: 'VERIFICATION',
    name: '核销审批提醒'
  },
  followUpExport: {
    value: 'FOLLOWUP_EXPORT',
    name: '跟进记录导出提醒'
  },
  judicial: {
    value: 'JUDICIAL',
    name: '司法审批提醒'
  },
  dowloadFile: {
    value: 'DOWNLOAD_FILE',
    name: '文件下载提醒'
  },
  caseImport: {
    value: 'CASE_IMPORT',
    name: '案件导入提醒'
  },
  strategy: {
    value: 'STRATEGY',
    name: '策略分配提醒'
  }
}

/**
 * 消息状态
 */
export const messageState = {
  read: {
    name: '已读消息',
    value: 'Read'
  },
  unRead: {
    name: '未读消息',
    value: 'UnRead'
  }
}

/**
 * 案件类型
 */
export enum caseType {
  /**
   * 电催
   */
  call = 15,
  /**
   * 外访
   */
  visit = 16,
  /**
   * 协催
   */
  assist = 17,
  /**
   * 委外
   */
  outside = 18,
  /**
   * 提醒
   */
  remind = 19,
  /**
   * other
   */
  complex = 217
}

// 案件状态
export enum caseState {
  /**
   * 20 待催收
   */
  waitCollection = 20,
  /**
   * 21 催收中
   */
  collection = 21,
  /**
   * 22 逾期还款中
   */
  overPaying = 22,
  /**
   * 提前结清还款中
   */
  earlyPaying = 23,
  /**
   * 24 已结案
   */
  caseOver = 24,
  /**
   * 25 待分配
   */
  waitForDis = 25,
  /**
   * 166 已委外
   */
  caseOut = 166,
  /**
   * 171 已还款
   */
  repaid = 171,
  /**
   * 172 部分已还款
   */
  partRepaid = 172
}

/**
 * 跟进类型
 */
export enum followType {
  /**
   * 电话催收
   */
  call = 78,
  /**
   * 外访催收
   */
  visit = 79,
  /**
   * 协助催收
   */
  assist = 162
}

/**
 * 跟进记录数据来源
 */
export enum sourceType {
  /**
   * 电话
   */
  call = 80,
  /**
   * 外访
   */
  visit = 81,
  /**
   * 协催
   */
  assist = 203
}

// 协催状态
export const assistState = {
  approveing: 26,  //协催审批中
  refused: 27, //协催拒绝
  collection: 28, //协催催收中
  complated: 29, //协催完成
  waitAssign: 117, //协催待分配
  waitAcc: 118, //协催待催收
  failure: 212 //协催审批失效
}

export const dashboardRankType = {
  amount: 0,
  count: 1,
  radio: 2
}

export const dashboardTimeType = {
  year: 0,
  month: 1,
  week: 2
}

export const dashboardQueryType = {
  inner: 1,
  outer: 2
}

export const templateType = {
  101: {
    code: 'MessageTemplate',
    name: '消息模版'
  },
  103: {
    code: 'EmailTemplate',
    name: '邮件模版'
  },
  104: {
    code: 'LetterTemplate',
    name: '信函模版'
  },
  105: {
    code: 'CallAccTemplate',
    name: '电催话术'
  }
}
export const templateVarType = [
  { name: '姓名', value: '$personalName' },
  { name: '金额', value: '$overdueAmount' },
  { name: '案件编号', value: '$caseNumber' },
  { name: '逾期日期', value: '$perdueDate' },
  { name: '合同编号', value: '$contractNumber' },
  { name: '合同金额', value: '$contractAmount' },
  { name: '逾期天数', value: '$overdueDays' },
  { name: '逾期实际还款金额', value: '$realPayAmount' },
  { name: '承诺还款日期', value: '$promiseTime' }
]
export const monthList = ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]

export const weekList = ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"]

/** 
 * 导出信息(设置更新型)基础数据信息
 * @prop baseDataOptions 各个维度得基本数据
 * @prop productBasic 产品信息基本数据
 * @prop productWork 产品信息工作信息
 * @prop productContact 产品信息联系人
 * @prop productOpenAccount 产品信息账户
*/
export const exportSelectionData = {
  // 基础数据
  baseDataOptions: ["机构名称", "客户姓名", "身份证号", "联系电话", "归属城市", "总期数", "逾期天数", "逾期金额", "贷款日期", "逾期期数"],
  // 基础数据信息
  productBasic: ["客户姓名", "身份证号", "归属城市", "手机号", "身份证户籍地址", "家庭地址", "家庭电话", "产品名称", "产品系列"],
  // 工作信息
  productWork: ["工作单位名称", "工作单位地址", "工作单位电话"],
  //联系人信息
  productContact: ["关系", "姓名", "手机号码", "住宅电话", "现居住地址", "工作单位", "单位电话"],
  //  开户信息
  productOpenAccount: ["还款卡银行", "还款卡号"]
}
export const selectionUpData = {
  // 客户基本信息
  productBasic: ["客户姓名", "身份证号", "手机号码", "身份证户籍地址", "家庭地址", "固定电话"],
  // 工作信息
  productWork: ["工作单位名称", "工作单位地址", "工作单位电话"],
  // 案件基本信息
  baseDataOptions: ['产品系列', '合同编号', '贷款日期', '合同金额', '剩余本金(元)', '剩余利息(元)', '逾期总金额(元)', '逾期本金(元)', '逾期利息(元)',
    '逾期罚息(元)', '还款期数', '每期还款金额(元)', '其他费用(元)', '逾期日期', '逾期天数',
    '已还款金额(元)', '已还款期数', '最近还款日期', '最近还款金额(元)', '佣金比例(%)'
  ],
  //  开户信息
  productOpenAccount: ["还款卡银行", "还款卡号"]
}
export const exportItemsUpData = {
  // 客户基本信息
  productBasic: ["客户姓名", "身份证号", "省份", "城市", "手机号码", "身份证户籍地址", "家庭地址", "固定电话"],
  // 工作信息
  productWork: ["工作单位名称", "工作单位地址", "工作单位电话"],
  //联系人信息
  productContact: ["姓名", "手机号码", "住宅电话", "现居住地址", "与客户关系", "工作单位", "单位电话"],
  // 案件基本信息
  baseDataOptions: ['产品系列', '合同编号', '贷款日期', '合同金额', '剩余本金(元)', '剩余利息(元)', '逾期总金额(元)', '逾期本金(元)', '逾期利息(元)',
    '逾期罚息(元)', '还款期数', '每期还款金额(元)', '其他费用(元)', '逾期日期', '逾期天数',
    '已还款金额(元)', '已还款期数', '最近还款日期', '最近还款金额(元)', '佣金比例(%)'
  ],
  //  开户信息
  productOpenAccount: ["还款卡银行", "还款卡号"],
  // 跟进信息
  followMessage: ["跟进时间", "跟进方式", "催收对象", "姓名", "电话/地址", "催收反馈", "跟进记录", "跟进人员"]
}
