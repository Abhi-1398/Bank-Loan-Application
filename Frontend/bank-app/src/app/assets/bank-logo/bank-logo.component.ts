import { ChangeDetectionStrategy,Component } from '@angular/core';
import {DomSanitizer} from '@angular/platform-browser';
import {MatIconRegistry, MatIconModule} from '@angular/material/icon';

const BANK_ICON =
  `
  <svg width="90" height="24" viewBox="0 0 90 24" fill="none" xmlns="http://www.w3.org/2000/svg">
  <path d="M36.0282 0H12.0282V24H36.0282V0Z" fill="white"/>
  <path d="M48.0282 12.0282L36.0282 0V24.0282L48.0282 12.0282Z" fill="#DB0011"/>
  <path d="M24.0282 12.0282L36.0282 0H12.0282L24.0282 12.0282Z" fill="#DB0011"/>
  <path d="M0 12.0282L12.0282 24.0282V0L0 12.0282Z" fill="#DB0011"/>
  <path d="M24.0282 12.0283L12.0282 24.0283H36.0282L24.0282 12.0283Z" fill="#DB0011"/>
  <path d="M58.56 12.7341H54.2118V17.0259H52.0377V6.97412H54.2118V11.0965H58.56V6.97412H60.7341V17.0259H58.56V12.7341Z" fill="black"/>
  <path d="M65.9859 17.2235C63.8118 17.2235 62.0329 16.3482 62.0047 13.9482H64.1788C64.2071 15.0212 64.8282 15.6706 66.0141 15.6706C66.8894 15.6706 67.9059 15.2188 67.9059 14.2306C67.9059 13.44 67.2282 13.2141 66.0988 12.8753L65.3647 12.6776C63.7835 12.2259 62.2023 11.6047 62.2023 9.79763C62.2023 7.56704 64.2918 6.80469 66.1835 6.80469C68.1318 6.80469 69.8259 7.48233 69.8541 9.71292H67.68C67.5953 8.80939 67.0588 8.27292 66.0423 8.27292C65.2235 8.27292 64.4329 8.69645 64.4329 9.59998C64.4329 10.3341 65.1106 10.56 66.5223 11.0117L67.3694 11.2659C69.0918 11.8023 70.1929 12.3953 70.1929 14.0894C70.1647 16.3482 67.9623 17.2235 65.9859 17.2235Z" fill="black"/>
  <path d="M71.407 7.00237H74.9082C75.5576 6.97414 76.2353 7.00237 76.8847 7.11531C78.0988 7.39767 79.0306 8.18825 79.0306 9.54355C79.0306 10.8424 78.2117 11.4918 77.0259 11.8024C78.3812 12.0565 79.3976 12.7341 79.3976 14.2306C79.3976 16.5177 77.1388 17.0259 75.3882 17.0259H71.4353L71.407 7.00237ZM74.9082 11.1812C75.8682 11.1812 76.8565 10.9835 76.8565 9.8259C76.8565 8.7812 75.9529 8.49884 75.0494 8.49884H73.5247V11.1812H74.9082ZM75.1059 15.5294C76.1223 15.5294 77.1106 15.3035 77.1106 14.0612C77.1106 12.8188 76.2635 12.593 75.2188 12.593H73.4965V15.5294H75.1059Z" fill="black"/>
  <path d="M85.0447 17.2235C81.7977 17.2235 80.3577 15.1624 80.3577 12.0847C80.3577 9.00708 81.9671 6.77649 85.1577 6.77649C87.1624 6.77649 89.1106 7.68002 89.1671 9.93884H86.9082C86.7953 8.92237 86.1177 8.41414 85.1577 8.41414C83.1812 8.41414 82.5883 10.5318 82.5883 12.1412C82.5883 13.7506 83.1812 15.6141 85.073 15.6141C86.0612 15.6141 86.7953 15.0777 86.9365 14.0612H89.1953C88.9694 16.3765 87.1341 17.2235 85.0447 17.2235Z" fill="black"/>
  </svg>
`;

@Component({
  selector: 'app-bank-logo',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './bank-logo.component.html',
  styleUrl: './bank-logo.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class BankLogoComponent {
  constructor(iconRegistry: MatIconRegistry, sanitizer: DomSanitizer) {
    iconRegistry.addSvgIconLiteral('bank-logo', sanitizer.bypassSecurityTrustHtml(BANK_ICON));
  }
}


