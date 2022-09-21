import store from '~/store'
import * as enumData from '~/config/enum.config'

const unit = 25

export class HelperService {
  static getColumnWidth(count) {
    return count * unit
  }
}
