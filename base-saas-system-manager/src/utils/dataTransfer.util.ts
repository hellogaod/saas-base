import Vue from 'vue'

//数据类型转换
export class DataTransferUtil {

  /**
   * 格式化数据成树形结构
   */
  static treeToArray(data, parent, level, expandedAll) {
    let tmp: Array<any> = []
    Array.from(data).forEach(function (record: any) {
      if (record._expanded === undefined) {
        Vue.set(record, '_expanded', expandedAll)
      }
      if (parent) {
        Vue.set(record, '_parent', parent)
      }
      let _level = 0
      if (level !== undefined && level !== null) {
        _level = level + 1
      }
      Vue.set(record, '_level', _level)
      tmp.push(record)
      if (record.children && record.children.length > 0) {
        let children = DataTransferUtil.treeToArray(record.children, record, _level, expandedAll)
        tmp = tmp.concat(children)
      }
    })
    return tmp

  }

  /**
   * 生成树型结构数据
   * @param sourceList 包含id 和 pid 的线性数据
   * @param options 数据项配置 配置 keyName: 主键名称,parentKeyName: 父级键名称
   */
  static generateTreeData(sourceList, options?: { keyName: string; parentKeyName: string; }): Array<any> {
    if (!sourceList) {
      return []
    }

    // key
    let keyName = options ? options.keyName : 'id';
    // parentKey
    let parentKeyName = options ? options.parentKeyName : 'pid';

    /**
     * 用当前节点去生成他的children节点
     * @param any node
     */
    let fun = (node: any) => {
      // 用找到的孩子节点去递归查找孙子节点
      let children = sourceList.filter(x => x[parentKeyName] === node[keyName]).map(fun);

      // 如果当前节点有孩子节点
      if (children && children.length) {
        // 就返回当包含孩子节点的对象
        return Object.assign({}, node, {children})
      } else {
        // 否则就返回当前对象
        return node
      }
    };

    let rootList: Array<any> = [];
    // 遍历列表中的每一行数据
    sourceList.forEach(current => {
      // 如果当前行数据不包含parentKey 那么就当作root节点之一
      if (!current[parentKeyName]) {
        return true;
      }
      // 用当前指定的数据去判断，列表中是否含有他的子节点数据
      let result = sourceList.find(item => item[keyName] === current[parentKeyName])
      // 如果没有找到子节点，当前这条数据就是根节点数据之一
      if (!result) rootList.push(current)

    });

    return rootList.map(fun)
  }
}
