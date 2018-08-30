import {Toast} from "angular2-toaster";

export class ToastBuilder {

  constructor() {

  }

  static successDeleteItem(): Toast {
    return {
      type: 'success',
      title: 'Item deleted successfully.',
      body: ''
    };
  }

  static successAddItem(): Toast {
    return {
      type: 'success',
      title: 'Item added successfully',
      body: ''
    };
  }

  static successUpdateItem(): Toast {
    return {
      type: 'success',
      title: 'Item updated successfully',
      body: ''
    };
  }


}
