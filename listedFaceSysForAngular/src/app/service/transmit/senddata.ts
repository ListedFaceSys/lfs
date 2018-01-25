import { Injectable } from '@angular/core';

@Injectable()
export class SendDataService {
  constructor() {
  }
  private id;

  setId(id){
    this.id = id;
  }
  getId(){
    return this.id;
  }

}
