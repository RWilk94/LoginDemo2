import {Toast} from "angular2-toaster";

export class ToastBuilder {

  constructor() {

  }

  static successDeleteItem(): Toast {
    return {
      type: 'success',
      title: 'Item deleted successfully',
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

  static errorWhileAddingItem() : Toast {
    return {
      type: 'error',
      title: 'Error while adding item',
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

  static errorEmptyName() : Toast {
    return {
      type: 'error',
      title: 'Name can not be empty',
      body: ''
    };
  }

  static errorEmptyValue() : Toast {
    return {
      type: 'error',
      title: 'Value can not be empty',
      body: ''
    };
  }

  static errorEmptyCategory() : Toast {
    return {
      type: 'error',
      title: 'Category can not be empty',
      body: ''
    };
  }

  static errorEmptyDate() : Toast {
    return {
      type: 'error',
      title: 'Date can not be empty',
      body: ''
    };
  }

  static warningTemplateForRecordAlreadyAdded() : Toast {
    return {
      type: 'warning',
      title: 'Template for the record already added',
      body: ''
    };
  }



}
