import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { ViserasExtremaduraSurSharedModule } from 'app/shared/shared.module';
import { ViserasExtremaduraSurCoreModule } from 'app/core/core.module';
import { ViserasExtremaduraSurAppRoutingModule } from './app-routing.module';
import { ViserasExtremaduraSurHomeModule } from './home/home.module';
import { ViserasExtremaduraSurEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    ViserasExtremaduraSurSharedModule,
    ViserasExtremaduraSurCoreModule,
    ViserasExtremaduraSurHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    ViserasExtremaduraSurEntityModule,
    ViserasExtremaduraSurAppRoutingModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent],
  bootstrap: [MainComponent]
})
export class ViserasExtremaduraSurAppModule {}
