import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <app-header></app-header>
    <div class="u-enter">
      <ul>
        <li>登录</li>
        <li>注册</li>
      </ul>
    </div>
    <div class="virtual-body">
      <router-outlet></router-outlet>
    </div>
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor() { };
}
