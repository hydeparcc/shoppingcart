import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ProductsComponent } from './products/products.component';
import { ConfirmationComponent } from './confirmation/confirmation.component';

const routes: Routes = [
  { path: 'products', component: ProductsComponent },
  { path: 'confirmation', component: ConfirmationComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
