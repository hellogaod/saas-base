<template>
  <section class="component icon-dialog">
    <div id="chooseicon">
      <ul class="list-inline">
        <li data-font="500px" data-toggle="tooltip" @click="choose($event)" title="500px">
          <i class="fa fa-500px"></i>
        </li>
        <li data-font="address-book" data-toggle="tooltip" @click="choose($event)" title="address-book">
          <i class="fa fa-address-book"></i>
        </li>
        <li data-font="address-book-o" data-toggle="tooltip" @click="choose($event)" title="address-book-o">
          <i class="fa fa-address-book-o"></i>
        </li>
        <li data-font="address-card" data-toggle="tooltip" @click="choose($event)" title="address-card">
          <i class="fa fa-address-card"></i>
        </li>
        <li data-font="address-card-o" data-toggle="tooltip" @click="choose($event)" title="address-card-o">
          <i class="fa fa-address-card-o"></i>
        </li>
        <li data-font="adjust" data-toggle="tooltip" @click="choose($event)" title="adjust">
          <i class="fa fa-adjust"></i>
        </li>
        <li data-font="adn" data-toggle="tooltip" @click="choose($event)" title="adn">
          <i class="fa fa-adn"></i>
        </li>
        <li data-font="align-center" data-toggle="tooltip" @click="choose($event)" title="align-center">
          <i class="fa fa-align-center"></i>
        </li>
        <li data-font="align-justify" data-toggle="tooltip" @click="choose($event)" title="align-justify">
          <i class="fa fa-align-justify"></i>
        </li>
        <li data-font="align-left" data-toggle="tooltip" @click="choose($event)" title="align-left">
          <i class="fa fa-align-left"></i>
        </li>
        <li data-font="align-right" data-toggle="tooltip" @click="choose($event)" title="align-right">
          <i class="fa fa-align-right"></i>
        </li>
        <li data-font="amazon" data-toggle="tooltip" @click="choose($event)" title="amazon">
          <i class="fa fa-amazon"></i>
        </li>
        <li data-font="ambulance" data-toggle="tooltip" @click="choose($event)" title="ambulance">
          <i class="fa fa-ambulance"></i>
        </li>
        <li data-font="american-sign-language-interpreting" data-toggle="tooltip" @click="choose($event)"
            title="american-sign-language-interpreting">
          <i class="fa fa-american-sign-language-interpreting"></i>
        </li>
        <li data-font="anchor" data-toggle="tooltip" @click="choose($event)" title="anchor">
          <i class="fa fa-anchor"></i>
        </li>
        <li data-font="android" data-toggle="tooltip" @click="choose($event)" title="" data-original-title="android">
          <i class="fa fa-android"></i>
        </li>
        <li data-font="angellist" data-toggle="tooltip" @click="choose($event)" title="angellist">
          <i class="fa fa-angellist"></i>
        </li>
        <li data-font="angle-double-down" data-toggle="tooltip" @click="choose($event)" title="angle-double-down">
          <i class="fa fa-angle-double-down"></i>
        </li>
        <li data-font="angle-double-left" data-toggle="tooltip" @click="choose($event)" title="angle-double-left">
          <i class="fa fa-angle-double-left"></i>
        </li>
        <li data-font="angle-double-right" data-toggle="tooltip" @click="choose($event)" title="angle-double-right">
          <i class="fa fa-angle-double-right"></i>
        </li>
        <li data-font="angle-double-up" data-toggle="tooltip" @click="choose($event)" title="angle-double-up">
          <i class="fa fa-angle-double-up"></i>
        </li>
        <li data-font="angle-down" data-toggle="tooltip" @click="choose($event)" title="angle-down">
          <i class="fa fa-angle-down"></i>
        </li>
        <li data-font="angle-left" data-toggle="tooltip" @click="choose($event)" title="angle-left">
          <i class="fa fa-angle-left"></i>
        </li>
        <li data-font="angle-right" data-toggle="tooltip" @click="choose($event)" title="angle-right">
          <i class="fa fa-angle-right"></i>
        </li>
        <li data-font="angle-up" data-toggle="tooltip" @click="choose($event)" title="angle-up">
          <i class="fa fa-angle-up"></i>
        </li>
        <li data-font="apple" data-toggle="tooltip" @click="choose($event)" title="apple">
          <i class="fa fa-apple"></i>
        </li>
        <li data-font="archive" data-toggle="tooltip" @click="choose($event)" title="archive">
          <i class="fa fa-archive"></i>
        </li>
        <li data-font="area-chart" data-toggle="tooltip" @click="choose($event)" title="area-chart">
          <i class="fa fa-area-chart"></i>
        </li>
        <li data-font="arrow-circle-down" data-toggle="tooltip" @click="choose($event)" title="arrow-circle-down">
          <i class="fa fa-arrow-circle-down"></i>
        </li>
        <li data-font="arrow-circle-left" data-toggle="tooltip" @click="choose($event)" title="arrow-circle-left">
          <i class="fa fa-arrow-circle-left"></i>
        </li>
        <li data-font="arrow-circle-o-down" data-toggle="tooltip" @click="choose($event)" title="arrow-circle-o-down">
          <i class="fa fa-arrow-circle-o-down"></i>
        </li>
        <li data-font="arrow-circle-o-left" data-toggle="tooltip" @click="choose($event)" title="arrow-circle-o-left">
          <i class="fa fa-arrow-circle-o-left"></i>
        </li>
        <li data-font="arrow-circle-o-right" data-toggle="tooltip" @click="choose($event)" title="arrow-circle-o-right">
          <i class="fa fa-arrow-circle-o-right"></i>
        </li>
        <li data-font="arrow-circle-o-up" data-toggle="tooltip" @click="choose($event)" title="arrow-circle-o-up">
          <i class="fa fa-arrow-circle-o-up"></i>
        </li>
        <li data-font="arrow-circle-right" data-toggle="tooltip" @click="choose($event)" title="arrow-circle-right">
          <i class="fa fa-arrow-circle-right"></i>
        </li>
        <li data-font="arrow-circle-up" data-toggle="tooltip" @click="choose($event)" title="arrow-circle-up">
          <i class="fa fa-arrow-circle-up"></i>
        </li>
        <li data-font="arrow-down" data-toggle="tooltip" @click="choose($event)" title="arrow-down">
          <i class="fa fa-arrow-down"></i>
        </li>
        <li data-font="arrow-left" data-toggle="tooltip" @click="choose($event)" title="arrow-left">
          <i class="fa fa-arrow-left"></i>
        </li>
        <li data-font="arrow-right" data-toggle="tooltip" @click="choose($event)" title="arrow-right">
          <i class="fa fa-arrow-right"></i>
        </li>
        <li data-font="arrow-up" data-toggle="tooltip" @click="choose($event)" title="arrow-up">
          <i class="fa fa-arrow-up"></i>
        </li>
        <li data-font="arrows" data-toggle="tooltip" @click="choose($event)" title="arrows">
          <i class="fa fa-arrows"></i>
        </li>
        <li data-font="arrows-alt" data-toggle="tooltip" @click="choose($event)" title="arrows-alt">
          <i class="fa fa-arrows-alt"></i>
        </li>
        <li data-font="arrows-h" data-toggle="tooltip" @click="choose($event)" title="arrows-h">
          <i class="fa fa-arrows-h"></i>
        </li>
        <li data-font="arrows-v" data-toggle="tooltip" @click="choose($event)" title="arrows-v">
          <i class="fa fa-arrows-v"></i>
        </li>
        <li data-font="asl-interpreting" data-toggle="tooltip" @click="choose($event)" title=""
            data-original-title="asl-interpreting">
          <i class="fa fa-asl-interpreting"></i>
        </li>
        <li data-font="assistive-listening-systems" data-toggle="tooltip" @click="choose($event)"
            title="assistive-listening-systems">
          <i class="fa fa-assistive-listening-systems"></i>
        </li>
        <li data-font="asterisk" data-toggle="tooltip" @click="choose($event)" title="asterisk">
          <i class="fa fa-asterisk"></i>
        </li>
        <li data-font="at" data-toggle="tooltip" @click="choose($event)" title="at">
          <i class="fa fa-at"></i>
        </li>
        <li data-font="audio-description" data-toggle="tooltip" @click="choose($event)" title="audio-description">
          <i class="fa fa-audio-description"></i>
        </li>
        <li data-font="automobile" data-toggle="tooltip" @click="choose($event)" title="automobile">
          <i class="fa fa-automobile"></i>
        </li>
        <li data-font="backward" data-toggle="tooltip" @click="choose($event)" title="backward">
          <i class="fa fa-backward"></i>
        </li>
        <li data-font="balance-scale" data-toggle="tooltip" @click="choose($event)" title="balance-scale">
          <i class="fa fa-balance-scale"></i>
        </li>
        <li data-font="ban" data-toggle="tooltip" @click="choose($event)" title="ban">
          <i class="fa fa-ban"></i>
        </li>
        <li data-font="bandcamp" data-toggle="tooltip" @click="choose($event)" title="bandcamp">
          <i class="fa fa-bandcamp"></i>
        </li>
        <li data-font="bank" data-toggle="tooltip" @click="choose($event)" title="bank">
          <i class="fa fa-bank"></i>
        </li>
        <li data-font="bar-chart" data-toggle="tooltip" @click="choose($event)" title="bar-chart">
          <i class="fa fa-bar-chart"></i>
        </li>
        <li data-font="bar-chart-o" data-toggle="tooltip" @click="choose($event)" title="bar-chart-o">
          <i class="fa fa-bar-chart-o"></i>
        </li>
        <li data-font="barcode" data-toggle="tooltip" @click="choose($event)" title="barcode">
          <i class="fa fa-barcode"></i>
        </li>
        <li data-font="bars" data-toggle="tooltip" @click="choose($event)" title="bars">
          <i class="fa fa-bars"></i>
        </li>
        <li data-font="bath" data-toggle="tooltip" @click="choose($event)" title="bath">
          <i class="fa fa-bath"></i>
        </li>
        <li data-font="bathtub" data-toggle="tooltip" @click="choose($event)" title="bathtub">
          <i class="fa fa-bathtub"></i>
        </li>
        <li data-font="battery" data-toggle="tooltip" @click="choose($event)" title="battery">
          <i class="fa fa-battery"></i>
        </li>
        <li data-font="battery-0" data-toggle="tooltip" @click="choose($event)" title="battery-0">
          <i class="fa fa-battery-0"></i>
        </li>
        <li data-font="battery-1" data-toggle="tooltip" @click="choose($event)" title="battery-1">
          <i class="fa fa-battery-1"></i>
        </li>
        <li data-font="battery-2" data-toggle="tooltip" @click="choose($event)" title="battery-2">
          <i class="fa fa-battery-2"></i>
        </li>
        <li data-font="battery-3" data-toggle="tooltip" @click="choose($event)" title="battery-3">
          <i class="fa fa-battery-3"></i>
        </li>
        <li data-font="battery-4" data-toggle="tooltip" @click="choose($event)" title="battery-4">
          <i class="fa fa-battery-4"></i>
        </li>
        <li data-font="battery-empty" data-toggle="tooltip" @click="choose($event)" title="battery-empty">
          <i class="fa fa-battery-empty"></i>
        </li>
        <li data-font="battery-full" data-toggle="tooltip" @click="choose($event)" title="battery-full">
          <i class="fa fa-battery-full"></i>
        </li>
        <li data-font="battery-half" data-toggle="tooltip" @click="choose($event)" title="battery-half">
          <i class="fa fa-battery-half"></i>
        </li>
        <li data-font="battery-quarter" data-toggle="tooltip" @click="choose($event)" title="battery-quarter">
          <i class="fa fa-battery-quarter"></i>
        </li>
        <li data-font="battery-three-quarters" data-toggle="tooltip" @click="choose($event)"
            title="battery-three-quarters">
          <i class="fa fa-battery-three-quarters"></i>
        </li>
        <li data-font="bed" data-toggle="tooltip" @click="choose($event)" title="bed">
          <i class="fa fa-bed"></i>
        </li>
        <li data-font="beer" data-toggle="tooltip" @click="choose($event)" title="beer">
          <i class="fa fa-beer"></i>
        </li>
        <li data-font="behance" data-toggle="tooltip" @click="choose($event)" title="behance">
          <i class="fa fa-behance"></i>
        </li>
        <li data-font="behance-square" data-toggle="tooltip" @click="choose($event)" title="behance-square">
          <i class="fa fa-behance-square"></i>
        </li>
        <li data-font="bell" data-toggle="tooltip" @click="choose($event)" title="bell">
          <i class="fa fa-bell"></i>
        </li>
        <li data-font="bell-o" data-toggle="tooltip" @click="choose($event)" title="bell-o">
          <i class="fa fa-bell-o"></i>
        </li>
        <li data-font="bell-slash" data-toggle="tooltip" @click="choose($event)" title="bell-slash">
          <i class="fa fa-bell-slash"></i>
        </li>
        <li data-font="bell-slash-o" data-toggle="tooltip" @click="choose($event)" title="bell-slash-o">
          <i class="fa fa-bell-slash-o"></i>
        </li>
        <li data-font="bicycle" data-toggle="tooltip" @click="choose($event)" title="bicycle">
          <i class="fa fa-bicycle"></i>
        </li>
        <li data-font="binoculars" data-toggle="tooltip" @click="choose($event)" title="binoculars">
          <i class="fa fa-binoculars"></i>
        </li>
        <li data-font="birthday-cake" data-toggle="tooltip" @click="choose($event)" title="birthday-cake">
          <i class="fa fa-birthday-cake"></i>
        </li>
        <li data-font="bitbucket" data-toggle="tooltip" @click="choose($event)" title="bitbucket">
          <i class="fa fa-bitbucket"></i>
        </li>
        <li data-font="bitbucket-square" data-toggle="tooltip" @click="choose($event)" title="bitbucket-square">
          <i class="fa fa-bitbucket-square"></i>
        </li>
        <li data-font="bitcoin" data-toggle="tooltip" @click="choose($event)" title="bitcoin">
          <i class="fa fa-bitcoin"></i>
        </li>
        <li data-font="black-tie" data-toggle="tooltip" @click="choose($event)" title="black-tie">
          <i class="fa fa-black-tie"></i>
        </li>
        <li data-font="blind" data-toggle="tooltip" @click="choose($event)" title="blind">
          <i class="fa fa-blind"></i>
        </li>
        <li data-font="bluetooth" data-toggle="tooltip" @click="choose($event)" title="bluetooth">
          <i class="fa fa-bluetooth"></i>
        </li>
        <li data-font="bluetooth-b" data-toggle="tooltip" @click="choose($event)" title="bluetooth-b">
          <i class="fa fa-bluetooth-b"></i>
        </li>
        <li data-font="bold" data-toggle="tooltip" @click="choose($event)" title="bold">
          <i class="fa fa-bold"></i>
        </li>
        <li data-font="bolt" data-toggle="tooltip" @click="choose($event)" title="bolt">
          <i class="fa fa-bolt"></i>
        </li>
        <li data-font="bomb" data-toggle="tooltip" @click="choose($event)" title="bomb">
          <i class="fa fa-bomb"></i>
        </li>
        <li data-font="book" data-toggle="tooltip" @click="choose($event)" title="book">
          <i class="fa fa-book"></i>
        </li>
        <li data-font="bookmark" data-toggle="tooltip" @click="choose($event)" title="bookmark">
          <i class="fa fa-bookmark"></i>
        </li>
        <li data-font="bookmark-o" data-toggle="tooltip" @click="choose($event)" title="bookmark-o">
          <i class="fa fa-bookmark-o"></i>
        </li>
        <li data-font="braille" data-toggle="tooltip" @click="choose($event)" title="braille">
          <i class="fa fa-braille"></i>
        </li>
        <li data-font="briefcase" data-toggle="tooltip" @click="choose($event)" title="briefcase">
          <i class="fa fa-briefcase"></i>
        </li>

        <li data-font="btc" data-toggle="tooltip" @click="choose($event)" title="btc">
          <i class="fa fa-btc"></i>
        </li>

        <li data-font="bug" data-toggle="tooltip" @click="choose($event)" title="bug">
          <i class="fa fa-bug"></i>
        </li>

        <li data-font="building" data-toggle="tooltip" @click="choose($event)" title="building">
          <i class="fa fa-building"></i>
        </li>

        <li data-font="building-o" data-toggle="tooltip" @click="choose($event)" title="building-o">
          <i class="fa fa-building-o"></i>
        </li>

        <li data-font="bullhorn" data-toggle="tooltip" @click="choose($event)" title="bullhorn">
          <i class="fa fa-bullhorn"></i>
        </li>

        <li data-font="bullseye" data-toggle="tooltip" @click="choose($event)" title="bullseye">
          <i class="fa fa-bullseye"></i>
        </li>

        <li data-font="bus" data-toggle="tooltip" @click="choose($event)" title="bus">
          <i class="fa fa-bus"></i>
        </li>

        <li data-font="buysellads" data-toggle="tooltip" @click="choose($event)" title="buysellads">
          <i class="fa fa-buysellads"></i>
        </li>

        <li data-font="cab" data-toggle="tooltip" @click="choose($event)" title="cab">
          <i class="fa fa-cab"></i>
        </li>

        <li data-font="calculator" data-toggle="tooltip" @click="choose($event)" title="calculator">
          <i class="fa fa-calculator"></i>
        </li>

        <li data-font="calendar" data-toggle="tooltip" @click="choose($event)" title="calendar">
          <i class="fa fa-calendar"></i>
        </li>

        <li data-font="calendar-check-o" data-toggle="tooltip" @click="choose($event)" title="calendar-check-o">
          <i class="fa fa-calendar-check-o"></i>
        </li>

        <li data-font="calendar-minus-o" data-toggle="tooltip" @click="choose($event)" title="calendar-minus-o">
          <i class="fa fa-calendar-minus-o"></i>
        </li>

        <li data-font="calendar-o" data-toggle="tooltip" @click="choose($event)" title="calendar-o">
          <i class="fa fa-calendar-o"></i>
        </li>

        <li data-font="calendar-plus-o" data-toggle="tooltip" @click="choose($event)" title="calendar-plus-o">
          <i class="fa fa-calendar-plus-o"></i>
        </li>

        <li data-font="calendar-times-o" data-toggle="tooltip" @click="choose($event)" title="calendar-times-o">
          <i class="fa fa-calendar-times-o"></i>
        </li>

        <li data-font="camera" data-toggle="tooltip" @click="choose($event)" title="camera">
          <i class="fa fa-camera"></i>
        </li>

        <li data-font="camera-retro" data-toggle="tooltip" @click="choose($event)" title="camera-retro">
          <i class="fa fa-camera-retro"></i>
        </li>

        <li data-font="car" data-toggle="tooltip" @click="choose($event)" title="car">
          <i class="fa fa-car"></i>
        </li>

        <li data-font="caret-down" data-toggle="tooltip" @click="choose($event)" title="caret-down">
          <i class="fa fa-caret-down"></i>
        </li>

        <li data-font="caret-left" data-toggle="tooltip" @click="choose($event)" title="caret-left">
          <i class="fa fa-caret-left"></i>
        </li>

        <li data-font="caret-right" data-toggle="tooltip" @click="choose($event)" title="caret-right">
          <i class="fa fa-caret-right"></i>
        </li>

        <li data-font="caret-square-o-down" data-toggle="tooltip" @click="choose($event)" title="caret-square-o-down">
          <i class="fa fa-caret-square-o-down"></i>
        </li>

        <li data-font="caret-square-o-left" data-toggle="tooltip" @click="choose($event)" title="caret-square-o-left">
          <i class="fa fa-caret-square-o-left"></i>
        </li>

        <li data-font="caret-square-o-right" data-toggle="tooltip" @click="choose($event)" title="caret-square-o-right">
          <i class="fa fa-caret-square-o-right"></i>
        </li>

        <li data-font="caret-square-o-up" data-toggle="tooltip" @click="choose($event)" title="caret-square-o-up">
          <i class="fa fa-caret-square-o-up"></i>
        </li>

        <li data-font="caret-up" data-toggle="tooltip" @click="choose($event)" title="caret-up">
          <i class="fa fa-caret-up"></i>
        </li>

        <li data-font="cart-arrow-down" data-toggle="tooltip" @click="choose($event)" title="cart-arrow-down">
          <i class="fa fa-cart-arrow-down"></i>
        </li>

        <li data-font="cart-plus" data-toggle="tooltip" @click="choose($event)" title="cart-plus">
          <i class="fa fa-cart-plus"></i>
        </li>

        <li data-font="cc" data-toggle="tooltip" @click="choose($event)" title="cc">
          <i class="fa fa-cc"></i>
        </li>

        <li data-font="cc-amex" data-toggle="tooltip" @click="choose($event)" title="cc-amex">
          <i class="fa fa-cc-amex"></i>
        </li>

        <li data-font="cc-diners-club" data-toggle="tooltip" @click="choose($event)" title="cc-diners-club">
          <i class="fa fa-cc-diners-club"></i>
        </li>

        <li data-font="cc-discover" data-toggle="tooltip" @click="choose($event)" title="cc-discover">
          <i class="fa fa-cc-discover"></i>
        </li>

        <li data-font="cc-jcb" data-toggle="tooltip" @click="choose($event)" title="cc-jcb">
          <i class="fa fa-cc-jcb"></i>
        </li>

        <li data-font="cc-mastercard" data-toggle="tooltip" @click="choose($event)" title="cc-mastercard">
          <i class="fa fa-cc-mastercard"></i>
        </li>

        <li data-font="cc-paypal" data-toggle="tooltip" @click="choose($event)" title="cc-paypal">
          <i class="fa fa-cc-paypal"></i>
        </li>

        <li data-font="cc-stripe" data-toggle="tooltip" @click="choose($event)" title="cc-stripe">
          <i class="fa fa-cc-stripe"></i>
        </li>

        <li data-font="cc-visa" data-toggle="tooltip" @click="choose($event)" title="cc-visa">
          <i class="fa fa-cc-visa"></i>
        </li>

        <li data-font="certificate" data-toggle="tooltip" @click="choose($event)" title="certificate">
          <i class="fa fa-certificate"></i>
        </li>

        <li data-font="chain" data-toggle="tooltip" @click="choose($event)" title="chain">
          <i class="fa fa-chain"></i>
        </li>

        <li data-font="chain-broken" data-toggle="tooltip" @click="choose($event)" title="chain-broken">
          <i class="fa fa-chain-broken"></i>
        </li>

        <li data-font="check" data-toggle="tooltip" @click="choose($event)" title="check">
          <i class="fa fa-check"></i>
        </li>

        <li data-font="check-circle" data-toggle="tooltip" @click="choose($event)" title="check-circle">
          <i class="fa fa-check-circle"></i>
        </li>

        <li data-font="check-circle-o" data-toggle="tooltip" @click="choose($event)" title="check-circle-o">
          <i class="fa fa-check-circle-o"></i>
        </li>

        <li data-font="check-square" data-toggle="tooltip" @click="choose($event)" title="check-square">
          <i class="fa fa-check-square"></i>
        </li>

        <li data-font="check-square-o" data-toggle="tooltip" @click="choose($event)" title="check-square-o">
          <i class="fa fa-check-square-o"></i>
        </li>

        <li data-font="chevron-circle-down" data-toggle="tooltip" @click="choose($event)" title="chevron-circle-down">
          <i class="fa fa-chevron-circle-down"></i>
        </li>

        <li data-font="chevron-circle-left" data-toggle="tooltip" @click="choose($event)" title="chevron-circle-left">
          <i class="fa fa-chevron-circle-left"></i>
        </li>

        <li data-font="chevron-circle-right" data-toggle="tooltip" @click="choose($event)" title="chevron-circle-right">
          <i class="fa fa-chevron-circle-right"></i>
        </li>

        <li data-font="chevron-circle-up" data-toggle="tooltip" @click="choose($event)" title="chevron-circle-up">
          <i class="fa fa-chevron-circle-up"></i>
        </li>

        <li data-font="chevron-down" data-toggle="tooltip" @click="choose($event)" title="chevron-down">
          <i class="fa fa-chevron-down"></i>
        </li>

        <li data-font="chevron-left" data-toggle="tooltip" @click="choose($event)" title="chevron-left">
          <i class="fa fa-chevron-left"></i>
        </li>

        <li data-font="chevron-right" data-toggle="tooltip" @click="choose($event)" title="chevron-right">
          <i class="fa fa-chevron-right"></i>
        </li>

        <li data-font="chevron-up" data-toggle="tooltip" @click="choose($event)" title="chevron-up">
          <i class="fa fa-chevron-up"></i>
        </li>

        <li data-font="child" data-toggle="tooltip" @click="choose($event)" title="child">
          <i class="fa fa-child"></i>
        </li>

        <li data-font="chrome" data-toggle="tooltip" @click="choose($event)" title="chrome">
          <i class="fa fa-chrome"></i>
        </li>

        <li data-font="circle" data-toggle="tooltip" @click="choose($event)" title="circle">
          <i class="fa fa-circle"></i>
        </li>

        <li data-font="circle-o" data-toggle="tooltip" @click="choose($event)" title="circle-o">
          <i class="fa fa-circle-o"></i>
        </li>

        <li data-font="circle-o-notch" data-toggle="tooltip" @click="choose($event)" title="circle-o-notch">
          <i class="fa fa-circle-o-notch"></i>
        </li>

        <li data-font="circle-thin" data-toggle="tooltip" @click="choose($event)" title="circle-thin">
          <i class="fa fa-circle-thin"></i>
        </li>

        <li data-font="clipboard" data-toggle="tooltip" @click="choose($event)" title="clipboard">
          <i class="fa fa-clipboard"></i>
        </li>

        <li data-font="clock-o" data-toggle="tooltip" @click="choose($event)" title="clock-o">
          <i class="fa fa-clock-o"></i>
        </li>

        <li data-font="clone" data-toggle="tooltip" @click="choose($event)" title="clone">
          <i class="fa fa-clone"></i>
        </li>

        <li data-font="close" data-toggle="tooltip" @click="choose($event)" title="close">
          <i class="fa fa-close"></i>
        </li>

        <li data-font="cloud" data-toggle="tooltip" @click="choose($event)" title="cloud">
          <i class="fa fa-cloud"></i>
        </li>

        <li data-font="cloud-download" data-toggle="tooltip" @click="choose($event)" title="cloud-download">
          <i class="fa fa-cloud-download"></i>
        </li>

        <li data-font="cloud-upload" data-toggle="tooltip" @click="choose($event)" title="cloud-upload">
          <i class="fa fa-cloud-upload"></i>
        </li>

        <li data-font="cny" data-toggle="tooltip" @click="choose($event)" title="cny">
          <i class="fa fa-cny"></i>
        </li>

        <li data-font="code" data-toggle="tooltip" @click="choose($event)" title="code">
          <i class="fa fa-code"></i>
        </li>

        <li data-font="code-fork" data-toggle="tooltip" @click="choose($event)" title="code-fork">
          <i class="fa fa-code-fork"></i>
        </li>

        <li data-font="codepen" data-toggle="tooltip" @click="choose($event)" title="codepen">
          <i class="fa fa-codepen"></i>
        </li>

        <li data-font="codiepie" data-toggle="tooltip" @click="choose($event)" title="codiepie">
          <i class="fa fa-codiepie"></i>
        </li>

        <li data-font="coffee" data-toggle="tooltip" @click="choose($event)" title="coffee">
          <i class="fa fa-coffee"></i>
        </li>

        <li data-font="cog" data-toggle="tooltip" @click="choose($event)" title="cog">
          <i class="fa fa-cog"></i>
        </li>

        <li data-font="cogs" data-toggle="tooltip" @click="choose($event)" title="cogs">
          <i class="fa fa-cogs"></i>
        </li>

        <li data-font="columns" data-toggle="tooltip" @click="choose($event)" title="columns">
          <i class="fa fa-columns"></i>
        </li>

        <li data-font="comment" data-toggle="tooltip" @click="choose($event)" title="comment">
          <i class="fa fa-comment"></i>
        </li>

        <li data-font="comment-o" data-toggle="tooltip" @click="choose($event)" title="comment-o">
          <i class="fa fa-comment-o"></i>
        </li>

        <li data-font="commenting" data-toggle="tooltip" @click="choose($event)" title="commenting">
          <i class="fa fa-commenting"></i>
        </li>

        <li data-font="commenting-o" data-toggle="tooltip" @click="choose($event)" title="commenting-o">
          <i class="fa fa-commenting-o"></i>
        </li>

        <li data-font="comments" data-toggle="tooltip" @click="choose($event)" title="comments">
          <i class="fa fa-comments"></i>
        </li>

        <li data-font="comments-o" data-toggle="tooltip" @click="choose($event)" title="comments-o">
          <i class="fa fa-comments-o"></i>
        </li>

        <li data-font="compass" data-toggle="tooltip" @click="choose($event)" title="compass">
          <i class="fa fa-compass"></i>
        </li>

        <li data-font="compress" data-toggle="tooltip" @click="choose($event)" title="compress">
          <i class="fa fa-compress"></i>
        </li>

        <li data-font="connectdevelop" data-toggle="tooltip" @click="choose($event)" title="connectdevelop">
          <i class="fa fa-connectdevelop"></i>
        </li>

        <li data-font="contao" data-toggle="tooltip" @click="choose($event)" title="contao">
          <i class="fa fa-contao"></i>
        </li>

        <li data-font="copy" data-toggle="tooltip" @click="choose($event)" title="copy">
          <i class="fa fa-copy"></i>
        </li>

        <li data-font="copyright" data-toggle="tooltip" @click="choose($event)" title="copyright">
          <i class="fa fa-copyright"></i>
        </li>

        <li data-font="creative-commons" data-toggle="tooltip" @click="choose($event)" title="creative-commons">
          <i class="fa fa-creative-commons"></i>
        </li>

        <li data-font="credit-card" data-toggle="tooltip" @click="choose($event)" title="credit-card">
          <i class="fa fa-credit-card"></i>
        </li>

        <li data-font="credit-card-alt" data-toggle="tooltip" @click="choose($event)" title="credit-card-alt">
          <i class="fa fa-credit-card-alt"></i>
        </li>

        <li data-font="crop" data-toggle="tooltip" @click="choose($event)" title="crop">
          <i class="fa fa-crop"></i>
        </li>

        <li data-font="crosshairs" data-toggle="tooltip" @click="choose($event)" title="crosshairs">
          <i class="fa fa-crosshairs"></i>
        </li>

        <li data-font="css3" data-toggle="tooltip" @click="choose($event)" title="css3">
          <i class="fa fa-css3"></i>
        </li>

        <li data-font="cube" data-toggle="tooltip" @click="choose($event)" title="cube">
          <i class="fa fa-cube"></i>
        </li>

        <li data-font="cubes" data-toggle="tooltip" @click="choose($event)" title="cubes">
          <i class="fa fa-cubes"></i>
        </li>

        <li data-font="cut" data-toggle="tooltip" @click="choose($event)" title="cut">
          <i class="fa fa-cut"></i>
        </li>

        <li data-font="cutlery" data-toggle="tooltip" @click="choose($event)" title="cutlery">
          <i class="fa fa-cutlery"></i>
        </li>

        <li data-font="dashboard" data-toggle="tooltip" @click="choose($event)" title="dashboard">
          <i class="fa fa-dashboard"></i>
        </li>

        <li data-font="dashcube" data-toggle="tooltip" @click="choose($event)" title="dashcube">
          <i class="fa fa-dashcube"></i>
        </li>

        <li data-font="database" data-toggle="tooltip" @click="choose($event)" title="database">
          <i class="fa fa-database"></i>
        </li>

        <li data-font="deaf" data-toggle="tooltip" @click="choose($event)" title="deaf">
          <i class="fa fa-deaf"></i>
        </li>

        <li data-font="deafness" data-toggle="tooltip" @click="choose($event)" title="deafness">
          <i class="fa fa-deafness"></i>
        </li>

        <li data-font="dedent" data-toggle="tooltip" @click="choose($event)" title="dedent">
          <i class="fa fa-dedent"></i>
        </li>

        <li data-font="delicious" data-toggle="tooltip" @click="choose($event)" title="delicious">
          <i class="fa fa-delicious"></i>
        </li>

        <li data-font="desktop" data-toggle="tooltip" @click="choose($event)" title="desktop">
          <i class="fa fa-desktop"></i>
        </li>

        <li data-font="deviantart" data-toggle="tooltip" @click="choose($event)" title="deviantart">
          <i class="fa fa-deviantart"></i>
        </li>

        <li data-font="diamond" data-toggle="tooltip" @click="choose($event)" title="diamond">
          <i class="fa fa-diamond"></i>
        </li>

        <li data-font="digg" data-toggle="tooltip" @click="choose($event)" title="digg">
          <i class="fa fa-digg"></i>
        </li>

        <li data-font="dollar" data-toggle="tooltip" @click="choose($event)" title="dollar">
          <i class="fa fa-dollar"></i>
        </li>

        <li data-font="dot-circle-o" data-toggle="tooltip" @click="choose($event)" title="dot-circle-o">
          <i class="fa fa-dot-circle-o"></i>
        </li>

        <li data-font="download" data-toggle="tooltip" @click="choose($event)" title="download">
          <i class="fa fa-download"></i>
        </li>

        <li data-font="dribbble" data-toggle="tooltip" @click="choose($event)" title="dribbble">
          <i class="fa fa-dribbble"></i>
        </li>

        <li data-font="drivers-license" data-toggle="tooltip" @click="choose($event)" title="drivers-license">
          <i class="fa fa-drivers-license"></i>
        </li>

        <li data-font="drivers-license-o" data-toggle="tooltip" @click="choose($event)" title="drivers-license-o">
          <i class="fa fa-drivers-license-o"></i>
        </li>

        <li data-font="dropbox" data-toggle="tooltip" @click="choose($event)" title="dropbox">
          <i class="fa fa-dropbox"></i>
        </li>

        <li data-font="drupal" data-toggle="tooltip" @click="choose($event)" title="drupal">
          <i class="fa fa-drupal"></i>
        </li>

        <li data-font="edge" data-toggle="tooltip" @click="choose($event)" title="edge">
          <i class="fa fa-edge"></i>
        </li>

        <li data-font="edit" data-toggle="tooltip" @click="choose($event)" title="edit">
          <i class="fa fa-edit"></i>
        </li>

        <li data-font="eercast" data-toggle="tooltip" @click="choose($event)" title="eercast">
          <i class="fa fa-eercast"></i>
        </li>

        <li data-font="eject" data-toggle="tooltip" @click="choose($event)" title="eject">
          <i class="fa fa-eject"></i>
        </li>

        <li data-font="ellipsis-h" data-toggle="tooltip" @click="choose($event)" title="ellipsis-h">
          <i class="fa fa-ellipsis-h"></i>
        </li>

        <li data-font="ellipsis-v" data-toggle="tooltip" @click="choose($event)" title="ellipsis-v">
          <i class="fa fa-ellipsis-v"></i>
        </li>

        <li data-font="empire" data-toggle="tooltip" @click="choose($event)" title="empire">
          <i class="fa fa-empire"></i>
        </li>

        <li data-font="envelope" data-toggle="tooltip" @click="choose($event)" title="envelope">
          <i class="fa fa-envelope"></i>
        </li>

        <li data-font="envelope-o" data-toggle="tooltip" @click="choose($event)" title="envelope-o">
          <i class="fa fa-envelope-o"></i>
        </li>

        <li data-font="envelope-open" data-toggle="tooltip" @click="choose($event)" title="envelope-open">
          <i class="fa fa-envelope-open"></i>
        </li>

        <li data-font="envelope-open-o" data-toggle="tooltip" @click="choose($event)" title="envelope-open-o">
          <i class="fa fa-envelope-open-o"></i>
        </li>

        <li data-font="envelope-square" data-toggle="tooltip" @click="choose($event)" title="envelope-square">
          <i class="fa fa-envelope-square"></i>
        </li>

        <li data-font="envira" data-toggle="tooltip" @click="choose($event)" title="envira">
          <i class="fa fa-envira"></i>
        </li>

        <li data-font="eraser" data-toggle="tooltip" @click="choose($event)" title="eraser">
          <i class="fa fa-eraser"></i>
        </li>

        <li data-font="etsy" data-toggle="tooltip" @click="choose($event)" title="etsy">
          <i class="fa fa-etsy"></i>
        </li>

        <li data-font="eur" data-toggle="tooltip" @click="choose($event)" title="eur">
          <i class="fa fa-eur"></i>
        </li>

        <li data-font="euro" data-toggle="tooltip" @click="choose($event)" title="euro">
          <i class="fa fa-euro"></i>
        </li>

        <li data-font="exchange" data-toggle="tooltip" @click="choose($event)" title="exchange">
          <i class="fa fa-exchange"></i>
        </li>

        <li data-font="exclamation" data-toggle="tooltip" @click="choose($event)" title="exclamation">
          <i class="fa fa-exclamation"></i>
        </li>

        <li data-font="exclamation-circle" data-toggle="tooltip" @click="choose($event)" title="exclamation-circle">
          <i class="fa fa-exclamation-circle"></i>
        </li>

        <li data-font="exclamation-triangle" data-toggle="tooltip" @click="choose($event)" title="exclamation-triangle">
          <i class="fa fa-exclamation-triangle"></i>
        </li>

        <li data-font="expand" data-toggle="tooltip" @click="choose($event)" title="expand">
          <i class="fa fa-expand"></i>
        </li>

        <li data-font="expeditedssl" data-toggle="tooltip" @click="choose($event)" title="expeditedssl">
          <i class="fa fa-expeditedssl"></i>
        </li>

        <li data-font="external-link" data-toggle="tooltip" @click="choose($event)" title="external-link">
          <i class="fa fa-external-link"></i>
        </li>

        <li data-font="external-link-square" data-toggle="tooltip" @click="choose($event)" title="external-link-square">
          <i class="fa fa-external-link-square"></i>
        </li>

        <li data-font="eye" data-toggle="tooltip" @click="choose($event)" title="eye">
          <i class="fa fa-eye"></i>
        </li>

        <li data-font="eye-slash" data-toggle="tooltip" @click="choose($event)" title="eye-slash">
          <i class="fa fa-eye-slash"></i>
        </li>

        <li data-font="eyedropper" data-toggle="tooltip" @click="choose($event)" title="eyedropper">
          <i class="fa fa-eyedropper"></i>
        </li>

        <li data-font="fa" data-toggle="tooltip" @click="choose($event)" title="fa">
          <i class="fa fa-fa"></i>
        </li>

        <li data-font="facebook" data-toggle="tooltip" @click="choose($event)" title="facebook">
          <i class="fa fa-facebook"></i>
        </li>

        <li data-font="facebook-f" data-toggle="tooltip" @click="choose($event)" title="facebook-f">
          <i class="fa fa-facebook-f"></i>
        </li>

        <li data-font="facebook-official" data-toggle="tooltip" @click="choose($event)" title="facebook-official">
          <i class="fa fa-facebook-official"></i>
        </li>

        <li data-font="facebook-square" data-toggle="tooltip" @click="choose($event)" title="facebook-square">
          <i class="fa fa-facebook-square"></i>
        </li>

        <li data-font="fast-backward" data-toggle="tooltip" @click="choose($event)" title="fast-backward">
          <i class="fa fa-fast-backward"></i>
        </li>

        <li data-font="fast-forward" data-toggle="tooltip" @click="choose($event)" title="fast-forward">
          <i class="fa fa-fast-forward"></i>
        </li>

        <li data-font="fax" data-toggle="tooltip" @click="choose($event)" title="fax">
          <i class="fa fa-fax"></i>
        </li>

        <li data-font="feed" data-toggle="tooltip" @click="choose($event)" title="feed">
          <i class="fa fa-feed"></i>
        </li>

        <li data-font="female" data-toggle="tooltip" @click="choose($event)" title="female">
          <i class="fa fa-female"></i>
        </li>

        <li data-font="fighter-jet" data-toggle="tooltip" @click="choose($event)" title="fighter-jet">
          <i class="fa fa-fighter-jet"></i>
        </li>

        <li data-font="file" data-toggle="tooltip" @click="choose($event)" title="file">
          <i class="fa fa-file"></i>
        </li>

        <li data-font="file-archive-o" data-toggle="tooltip" @click="choose($event)" title="file-archive-o">
          <i class="fa fa-file-archive-o"></i>
        </li>

        <li data-font="file-audio-o" data-toggle="tooltip" @click="choose($event)" title="file-audio-o">
          <i class="fa fa-file-audio-o"></i>
        </li>

        <li data-font="file-code-o" data-toggle="tooltip" @click="choose($event)" title="file-code-o">
          <i class="fa fa-file-code-o"></i>
        </li>

        <li data-font="file-excel-o" data-toggle="tooltip" @click="choose($event)" title="file-excel-o">
          <i class="fa fa-file-excel-o"></i>
        </li>

        <li data-font="file-image-o" data-toggle="tooltip" @click="choose($event)" title="file-image-o">
          <i class="fa fa-file-image-o"></i>
        </li>

        <li data-font="file-movie-o" data-toggle="tooltip" @click="choose($event)" title="file-movie-o">
          <i class="fa fa-file-movie-o"></i>
        </li>

        <li data-font="file-o" data-toggle="tooltip" @click="choose($event)" title="file-o">
          <i class="fa fa-file-o"></i>
        </li>

        <li data-font="file-pdf-o" data-toggle="tooltip" @click="choose($event)" title="file-pdf-o">
          <i class="fa fa-file-pdf-o"></i>
        </li>

        <li data-font="file-photo-o" data-toggle="tooltip" @click="choose($event)" title="file-photo-o">
          <i class="fa fa-file-photo-o"></i>
        </li>

        <li data-font="file-picture-o" data-toggle="tooltip" @click="choose($event)" title="file-picture-o">
          <i class="fa fa-file-picture-o"></i>
        </li>

        <li data-font="file-powerpoint-o" data-toggle="tooltip" @click="choose($event)" title="file-powerpoint-o">
          <i class="fa fa-file-powerpoint-o"></i>
        </li>

        <li data-font="file-sound-o" data-toggle="tooltip" @click="choose($event)" title="file-sound-o">
          <i class="fa fa-file-sound-o"></i>
        </li>

        <li data-font="file-text" data-toggle="tooltip" @click="choose($event)" title="file-text">
          <i class="fa fa-file-text"></i>
        </li>

        <li data-font="file-text-o" data-toggle="tooltip" @click="choose($event)" title="file-text-o">
          <i class="fa fa-file-text-o"></i>
        </li>

        <li data-font="file-video-o" data-toggle="tooltip" @click="choose($event)" title="file-video-o">
          <i class="fa fa-file-video-o"></i>
        </li>

        <li data-font="file-word-o" data-toggle="tooltip" @click="choose($event)" title="file-word-o">
          <i class="fa fa-file-word-o"></i>
        </li>

        <li data-font="file-zip-o" data-toggle="tooltip" @click="choose($event)" title="file-zip-o">
          <i class="fa fa-file-zip-o"></i>
        </li>

        <li data-font="files-o" data-toggle="tooltip" @click="choose($event)" title="files-o">
          <i class="fa fa-files-o"></i>
        </li>

        <li data-font="film" data-toggle="tooltip" @click="choose($event)" title="film">
          <i class="fa fa-film"></i>
        </li>

        <li data-font="filter" data-toggle="tooltip" @click="choose($event)" title="filter">
          <i class="fa fa-filter"></i>
        </li>

        <li data-font="fire" data-toggle="tooltip" @click="choose($event)" title="fire">
          <i class="fa fa-fire"></i>
        </li>

        <li data-font="fire-extinguisher" data-toggle="tooltip" @click="choose($event)" title="fire-extinguisher">
          <i class="fa fa-fire-extinguisher"></i>
        </li>

        <li data-font="firefox" data-toggle="tooltip" @click="choose($event)" title="firefox">
          <i class="fa fa-firefox"></i>
        </li>

        <li data-font="first-order" data-toggle="tooltip" @click="choose($event)" title="first-order">
          <i class="fa fa-first-order"></i>
        </li>

        <li data-font="flag" data-toggle="tooltip" @click="choose($event)" title="flag">
          <i class="fa fa-flag"></i>
        </li>

        <li data-font="flag-checkered" data-toggle="tooltip" @click="choose($event)" title="flag-checkered">
          <i class="fa fa-flag-checkered"></i>
        </li>

        <li data-font="flag-o" data-toggle="tooltip" @click="choose($event)" title="flag-o">
          <i class="fa fa-flag-o"></i>
        </li>

        <li data-font="flash" data-toggle="tooltip" @click="choose($event)" title="flash">
          <i class="fa fa-flash"></i>
        </li>

        <li data-font="flask" data-toggle="tooltip" @click="choose($event)" title="flask">
          <i class="fa fa-flask"></i>
        </li>

        <li data-font="flickr" data-toggle="tooltip" @click="choose($event)" title="flickr">
          <i class="fa fa-flickr"></i>
        </li>

        <li data-font="floppy-o" data-toggle="tooltip" @click="choose($event)" title="floppy-o">
          <i class="fa fa-floppy-o"></i>
        </li>

        <li data-font="folder" data-toggle="tooltip" @click="choose($event)" title="folder">
          <i class="fa fa-folder"></i>
        </li>

        <li data-font="folder-o" data-toggle="tooltip" @click="choose($event)" title="folder-o">
          <i class="fa fa-folder-o"></i>
        </li>

        <li data-font="folder-open" data-toggle="tooltip" @click="choose($event)" title="folder-open">
          <i class="fa fa-folder-open"></i>
        </li>

        <li data-font="folder-open-o" data-toggle="tooltip" @click="choose($event)" title="folder-open-o">
          <i class="fa fa-folder-open-o"></i>
        </li>

        <li data-font="font" data-toggle="tooltip" @click="choose($event)" title="font">
          <i class="fa fa-font"></i>
        </li>

        <li data-font="font-awesome" data-toggle="tooltip" @click="choose($event)" title="font-awesome">
          <i class="fa fa-font-awesome"></i>
        </li>

        <li data-font="fonticons" data-toggle="tooltip" @click="choose($event)" title="fonticons">
          <i class="fa fa-fonticons"></i>
        </li>

        <li data-font="fort-awesome" data-toggle="tooltip" @click="choose($event)" title="fort-awesome">
          <i class="fa fa-fort-awesome"></i>
        </li>

        <li data-font="forumbee" data-toggle="tooltip" @click="choose($event)" title="forumbee">
          <i class="fa fa-forumbee"></i>
        </li>

        <li data-font="forward" data-toggle="tooltip" @click="choose($event)" title="forward">
          <i class="fa fa-forward"></i>
        </li>

        <li data-font="foursquare" data-toggle="tooltip" @click="choose($event)" title="foursquare">
          <i class="fa fa-foursquare"></i>
        </li>

        <li data-font="free-code-camp" data-toggle="tooltip" @click="choose($event)" title="free-code-camp">
          <i class="fa fa-free-code-camp"></i>
        </li>

        <li data-font="frown-o" data-toggle="tooltip" @click="choose($event)" title="frown-o">
          <i class="fa fa-frown-o"></i>
        </li>

        <li data-font="futbol-o" data-toggle="tooltip" @click="choose($event)" title="futbol-o">
          <i class="fa fa-futbol-o"></i>
        </li>

        <li data-font="gamepad" data-toggle="tooltip" @click="choose($event)" title="gamepad">
          <i class="fa fa-gamepad"></i>
        </li>

        <li data-font="gavel" data-toggle="tooltip" @click="choose($event)" title="gavel">
          <i class="fa fa-gavel"></i>
        </li>

        <li data-font="gbp" data-toggle="tooltip" @click="choose($event)" title="gbp">
          <i class="fa fa-gbp"></i>
        </li>

        <li data-font="ge" data-toggle="tooltip" @click="choose($event)" title="ge">
          <i class="fa fa-ge"></i>
        </li>

        <li data-font="gear" data-toggle="tooltip" @click="choose($event)" title="gear">
          <i class="fa fa-gear"></i>
        </li>

        <li data-font="gears" data-toggle="tooltip" @click="choose($event)" title="gears">
          <i class="fa fa-gears"></i>
        </li>

        <li data-font="genderless" data-toggle="tooltip" @click="choose($event)" title="genderless">
          <i class="fa fa-genderless"></i>
        </li>

        <li data-font="get-pocket" data-toggle="tooltip" @click="choose($event)" title="get-pocket">
          <i class="fa fa-get-pocket"></i>
        </li>

        <li data-font="gg" data-toggle="tooltip" @click="choose($event)" title="gg">
          <i class="fa fa-gg"></i>
        </li>

        <li data-font="gg-circle" data-toggle="tooltip" @click="choose($event)" title="gg-circle">
          <i class="fa fa-gg-circle"></i>
        </li>

        <li data-font="gift" data-toggle="tooltip" @click="choose($event)" title="gift">
          <i class="fa fa-gift"></i>
        </li>

        <li data-font="git" data-toggle="tooltip" @click="choose($event)" title="git">
          <i class="fa fa-git"></i>
        </li>

        <li data-font="git-square" data-toggle="tooltip" @click="choose($event)" title="git-square">
          <i class="fa fa-git-square"></i>
        </li>

        <li data-font="github" data-toggle="tooltip" @click="choose($event)" title="github">
          <i class="fa fa-github"></i>
        </li>

        <li data-font="github-alt" data-toggle="tooltip" @click="choose($event)" title="github-alt">
          <i class="fa fa-github-alt"></i>
        </li>

        <li data-font="github-square" data-toggle="tooltip" @click="choose($event)" title="github-square">
          <i class="fa fa-github-square"></i>
        </li>

        <li data-font="gitlab" data-toggle="tooltip" @click="choose($event)" title="gitlab">
          <i class="fa fa-gitlab"></i>
        </li>

        <li data-font="gittip" data-toggle="tooltip" @click="choose($event)" title="gittip">
          <i class="fa fa-gittip"></i>
        </li>

        <li data-font="glass" data-toggle="tooltip" @click="choose($event)" title="glass">
          <i class="fa fa-glass"></i>
        </li>

        <li data-font="glide" data-toggle="tooltip" @click="choose($event)" title="glide">
          <i class="fa fa-glide"></i>
        </li>

        <li data-font="glide-g" data-toggle="tooltip" @click="choose($event)" title="glide-g">
          <i class="fa fa-glide-g"></i>
        </li>

        <li data-font="globe" data-toggle="tooltip" @click="choose($event)" title="globe">
          <i class="fa fa-globe"></i>
        </li>

        <li data-font="google" data-toggle="tooltip" @click="choose($event)" title="google">
          <i class="fa fa-google"></i>
        </li>

        <li data-font="google-plus" data-toggle="tooltip" @click="choose($event)" title="google-plus">
          <i class="fa fa-google-plus"></i>
        </li>

        <li data-font="google-plus-circle" data-toggle="tooltip" @click="choose($event)" title="google-plus-circle">
          <i class="fa fa-google-plus-circle"></i>
        </li>

        <li data-font="google-plus-official" data-toggle="tooltip" @click="choose($event)" title="google-plus-official">
          <i class="fa fa-google-plus-official"></i>
        </li>

        <li data-font="google-plus-square" data-toggle="tooltip" @click="choose($event)" title="google-plus-square">
          <i class="fa fa-google-plus-square"></i>
        </li>

        <li data-font="google-wallet" data-toggle="tooltip" @click="choose($event)" title="google-wallet">
          <i class="fa fa-google-wallet"></i>
        </li>

        <li data-font="graduation-cap" data-toggle="tooltip" @click="choose($event)" title="graduation-cap">
          <i class="fa fa-graduation-cap"></i>
        </li>

        <li data-font="gratipay" data-toggle="tooltip" @click="choose($event)" title="gratipay">
          <i class="fa fa-gratipay"></i>
        </li>

        <li data-font="grav" data-toggle="tooltip" @click="choose($event)" title="grav">
          <i class="fa fa-grav"></i>
        </li>

        <li data-font="group" data-toggle="tooltip" @click="choose($event)" title="group">
          <i class="fa fa-group"></i>
        </li>

        <li data-font="h-square" data-toggle="tooltip" @click="choose($event)" title="h-square">
          <i class="fa fa-h-square"></i>
        </li>

        <li data-font="hacker-news" data-toggle="tooltip" @click="choose($event)" title="hacker-news">
          <i class="fa fa-hacker-news"></i>
        </li>

        <li data-font="hand-grab-o" data-toggle="tooltip" @click="choose($event)" title="hand-grab-o">
          <i class="fa fa-hand-grab-o"></i>
        </li>

        <li data-font="hand-lizard-o" data-toggle="tooltip" @click="choose($event)" title="hand-lizard-o">
          <i class="fa fa-hand-lizard-o"></i>
        </li>

        <li data-font="hand-o-down" data-toggle="tooltip" @click="choose($event)" title="hand-o-down">
          <i class="fa fa-hand-o-down"></i>
        </li>

        <li data-font="hand-o-left" data-toggle="tooltip" @click="choose($event)" title="hand-o-left">
          <i class="fa fa-hand-o-left"></i>
        </li>

        <li data-font="hand-o-right" data-toggle="tooltip" @click="choose($event)" title="hand-o-right">
          <i class="fa fa-hand-o-right"></i>
        </li>

        <li data-font="hand-o-up" data-toggle="tooltip" @click="choose($event)" title="hand-o-up">
          <i class="fa fa-hand-o-up"></i>
        </li>

        <li data-font="hand-paper-o" data-toggle="tooltip" @click="choose($event)" title="hand-paper-o">
          <i class="fa fa-hand-paper-o"></i>
        </li>

        <li data-font="hand-peace-o" data-toggle="tooltip" @click="choose($event)" title="hand-peace-o">
          <i class="fa fa-hand-peace-o"></i>
        </li>

        <li data-font="hand-pointer-o" data-toggle="tooltip" @click="choose($event)" title="hand-pointer-o">
          <i class="fa fa-hand-pointer-o"></i>
        </li>

        <li data-font="hand-rock-o" data-toggle="tooltip" @click="choose($event)" title="hand-rock-o">
          <i class="fa fa-hand-rock-o"></i>
        </li>

        <li data-font="hand-scissors-o" data-toggle="tooltip" @click="choose($event)" title="hand-scissors-o">
          <i class="fa fa-hand-scissors-o"></i>
        </li>

        <li data-font="hand-spock-o" data-toggle="tooltip" @click="choose($event)" title="hand-spock-o">
          <i class="fa fa-hand-spock-o"></i>
        </li>

        <li data-font="hand-stop-o" data-toggle="tooltip" @click="choose($event)" title="hand-stop-o">
          <i class="fa fa-hand-stop-o"></i>
        </li>

        <li data-font="handshake-o" data-toggle="tooltip" @click="choose($event)" title="handshake-o">
          <i class="fa fa-handshake-o"></i>
        </li>

        <li data-font="hard-of-hearing" data-toggle="tooltip" @click="choose($event)" title="hard-of-hearing">
          <i class="fa fa-hard-of-hearing"></i>
        </li>

        <li data-font="hashtag" data-toggle="tooltip" @click="choose($event)" title="hashtag">
          <i class="fa fa-hashtag"></i>
        </li>

        <li data-font="hdd-o" data-toggle="tooltip" @click="choose($event)" title="hdd-o">
          <i class="fa fa-hdd-o"></i>
        </li>

        <li data-font="header" data-toggle="tooltip" @click="choose($event)" title="header">
          <i class="fa fa-header"></i>
        </li>

        <li data-font="headphones" data-toggle="tooltip" @click="choose($event)" title="headphones">
          <i class="fa fa-headphones"></i>
        </li>

        <li data-font="heart" data-toggle="tooltip" @click="choose($event)" title="heart">
          <i class="fa fa-heart"></i>
        </li>

        <li data-font="heart-o" data-toggle="tooltip" @click="choose($event)" title="heart-o">
          <i class="fa fa-heart-o"></i>
        </li>

        <li data-font="heartbeat" data-toggle="tooltip" @click="choose($event)" title="heartbeat">
          <i class="fa fa-heartbeat"></i>
        </li>

        <li data-font="history" data-toggle="tooltip" @click="choose($event)" title="history">
          <i class="fa fa-history"></i>
        </li>

        <li data-font="home" data-toggle="tooltip" @click="choose($event)" title="home">
          <i class="fa fa-home"></i>
        </li>

        <li data-font="hospital-o" data-toggle="tooltip" @click="choose($event)" title="hospital-o">
          <i class="fa fa-hospital-o"></i>
        </li>

        <li data-font="hotel" data-toggle="tooltip" @click="choose($event)" title="hotel">
          <i class="fa fa-hotel"></i>
        </li>

        <li data-font="hourglass" data-toggle="tooltip" @click="choose($event)" title="hourglass">
          <i class="fa fa-hourglass"></i>
        </li>

        <li data-font="hourglass-1" data-toggle="tooltip" @click="choose($event)" title="hourglass-1">
          <i class="fa fa-hourglass-1"></i>
        </li>

        <li data-font="hourglass-2" data-toggle="tooltip" @click="choose($event)" title="hourglass-2">
          <i class="fa fa-hourglass-2"></i>
        </li>

        <li data-font="hourglass-3" data-toggle="tooltip" @click="choose($event)" title="hourglass-3">
          <i class="fa fa-hourglass-3"></i>
        </li>

        <li data-font="hourglass-end" data-toggle="tooltip" @click="choose($event)" title="hourglass-end">
          <i class="fa fa-hourglass-end"></i>
        </li>

        <li data-font="hourglass-half" data-toggle="tooltip" @click="choose($event)" title="hourglass-half">
          <i class="fa fa-hourglass-half"></i>
        </li>

        <li data-font="hourglass-o" data-toggle="tooltip" @click="choose($event)" title="hourglass-o">
          <i class="fa fa-hourglass-o"></i>
        </li>

        <li data-font="hourglass-start" data-toggle="tooltip" @click="choose($event)" title="hourglass-start">
          <i class="fa fa-hourglass-start"></i>
        </li>

        <li data-font="houzz" data-toggle="tooltip" @click="choose($event)" title="houzz">
          <i class="fa fa-houzz"></i>
        </li>

        <li data-font="html5" data-toggle="tooltip" @click="choose($event)" title="html5">
          <i class="fa fa-html5"></i>
        </li>

        <li data-font="i-cursor" data-toggle="tooltip" @click="choose($event)" title="i-cursor">
          <i class="fa fa-i-cursor"></i>
        </li>

        <li data-font="id-badge" data-toggle="tooltip" @click="choose($event)" title="id-badge">
          <i class="fa fa-id-badge"></i>
        </li>

        <li data-font="id-card" data-toggle="tooltip" @click="choose($event)" title="id-card">
          <i class="fa fa-id-card"></i>
        </li>

        <li data-font="id-card-o" data-toggle="tooltip" @click="choose($event)" title="id-card-o">
          <i class="fa fa-id-card-o"></i>
        </li>

        <li data-font="ils" data-toggle="tooltip" @click="choose($event)" title="ils">
          <i class="fa fa-ils"></i>
        </li>

        <li data-font="image" data-toggle="tooltip" @click="choose($event)" title="image">
          <i class="fa fa-image"></i>
        </li>

        <li data-font="imdb" data-toggle="tooltip" @click="choose($event)" title="imdb">
          <i class="fa fa-imdb"></i>
        </li>

        <li data-font="inbox" data-toggle="tooltip" @click="choose($event)" title="inbox">
          <i class="fa fa-inbox"></i>
        </li>

        <li data-font="indent" data-toggle="tooltip" @click="choose($event)" title="indent">
          <i class="fa fa-indent"></i>
        </li>

        <li data-font="industry" data-toggle="tooltip" @click="choose($event)" title="industry">
          <i class="fa fa-industry"></i>
        </li>

        <li data-font="info" data-toggle="tooltip" @click="choose($event)" title="info">
          <i class="fa fa-info"></i>
        </li>

        <li data-font="info-circle" data-toggle="tooltip" @click="choose($event)" title="info-circle">
          <i class="fa fa-info-circle"></i>
        </li>

        <li data-font="inr" data-toggle="tooltip" @click="choose($event)" title="inr">
          <i class="fa fa-inr"></i>
        </li>

        <li data-font="instagram" data-toggle="tooltip" @click="choose($event)" title="instagram">
          <i class="fa fa-instagram"></i>
        </li>

        <li data-font="institution" data-toggle="tooltip" @click="choose($event)" title="institution">
          <i class="fa fa-institution"></i>
        </li>

        <li data-font="internet-explorer" data-toggle="tooltip" @click="choose($event)" title="internet-explorer">
          <i class="fa fa-internet-explorer"></i>
        </li>

        <li data-font="intersex" data-toggle="tooltip" @click="choose($event)" title="intersex">
          <i class="fa fa-intersex"></i>
        </li>

        <li data-font="ioxhost" data-toggle="tooltip" @click="choose($event)" title="ioxhost">
          <i class="fa fa-ioxhost"></i>
        </li>

        <li data-font="italic" data-toggle="tooltip" @click="choose($event)" title="italic">
          <i class="fa fa-italic"></i>
        </li>

        <li data-font="joomla" data-toggle="tooltip" @click="choose($event)" title="joomla">
          <i class="fa fa-joomla"></i>
        </li>

        <li data-font="jpy" data-toggle="tooltip" @click="choose($event)" title="jpy">
          <i class="fa fa-jpy"></i>
        </li>

        <li data-font="jsfiddle" data-toggle="tooltip" @click="choose($event)" title="jsfiddle">
          <i class="fa fa-jsfiddle"></i>
        </li>

        <li data-font="key" data-toggle="tooltip" @click="choose($event)" title="key">
          <i class="fa fa-key"></i>
        </li>

        <li data-font="keyboard-o" data-toggle="tooltip" @click="choose($event)" title="keyboard-o">
          <i class="fa fa-keyboard-o"></i>
        </li>

        <li data-font="krw" data-toggle="tooltip" @click="choose($event)" title="krw">
          <i class="fa fa-krw"></i>
        </li>

        <li data-font="language" data-toggle="tooltip" @click="choose($event)" title="language">
          <i class="fa fa-language"></i>
        </li>

        <li data-font="laptop" data-toggle="tooltip" @click="choose($event)" title="laptop">
          <i class="fa fa-laptop"></i>
        </li>

        <li data-font="lastfm" data-toggle="tooltip" @click="choose($event)" title="lastfm">
          <i class="fa fa-lastfm"></i>
        </li>

        <li data-font="lastfm-square" data-toggle="tooltip" @click="choose($event)" title="lastfm-square">
          <i class="fa fa-lastfm-square"></i>
        </li>

        <li data-font="leaf" data-toggle="tooltip" @click="choose($event)" title="leaf">
          <i class="fa fa-leaf"></i>
        </li>

        <li data-font="leanpub" data-toggle="tooltip" @click="choose($event)" title="leanpub">
          <i class="fa fa-leanpub"></i>
        </li>

        <li data-font="legal" data-toggle="tooltip" @click="choose($event)" title="legal">
          <i class="fa fa-legal"></i>
        </li>

        <li data-font="lemon-o" data-toggle="tooltip" @click="choose($event)" title="lemon-o">
          <i class="fa fa-lemon-o"></i>
        </li>

        <li data-font="level-down" data-toggle="tooltip" @click="choose($event)" title="level-down">
          <i class="fa fa-level-down"></i>
        </li>

        <li data-font="level-up" data-toggle="tooltip" @click="choose($event)" title="level-up">
          <i class="fa fa-level-up"></i>
        </li>

        <li data-font="life-bouy" data-toggle="tooltip" @click="choose($event)" title="life-bouy">
          <i class="fa fa-life-bouy"></i>
        </li>

        <li data-font="life-buoy" data-toggle="tooltip" @click="choose($event)" title="life-buoy">
          <i class="fa fa-life-buoy"></i>
        </li>

        <li data-font="life-ring" data-toggle="tooltip" @click="choose($event)" title="life-ring">
          <i class="fa fa-life-ring"></i>
        </li>

        <li data-font="life-saver" data-toggle="tooltip" @click="choose($event)" title="life-saver">
          <i class="fa fa-life-saver"></i>
        </li>

        <li data-font="lightbulb-o" data-toggle="tooltip" @click="choose($event)" title="lightbulb-o">
          <i class="fa fa-lightbulb-o"></i>
        </li>

        <li data-font="line-chart" data-toggle="tooltip" @click="choose($event)" title="line-chart">
          <i class="fa fa-line-chart"></i>
        </li>

        <li data-font="link" data-toggle="tooltip" @click="choose($event)" title="link">
          <i class="fa fa-link"></i>
        </li>

        <li data-font="linkedin" data-toggle="tooltip" @click="choose($event)" title="linkedin">
          <i class="fa fa-linkedin"></i>
        </li>

        <li data-font="linkedin-square" data-toggle="tooltip" @click="choose($event)" title="linkedin-square">
          <i class="fa fa-linkedin-square"></i>
        </li>

        <li data-font="linode" data-toggle="tooltip" @click="choose($event)" title="linode">
          <i class="fa fa-linode"></i>
        </li>

        <li data-font="linux" data-toggle="tooltip" @click="choose($event)" title="linux">
          <i class="fa fa-linux"></i>
        </li>

        <li data-font="list" data-toggle="tooltip" @click="choose($event)" title="list">
          <i class="fa fa-list"></i>
        </li>

        <li data-font="list-alt" data-toggle="tooltip" @click="choose($event)" title="list-alt">
          <i class="fa fa-list-alt"></i>
        </li>

        <li data-font="list-ol" data-toggle="tooltip" @click="choose($event)" title="list-ol">
          <i class="fa fa-list-ol"></i>
        </li>

        <li data-font="list-ul" data-toggle="tooltip" @click="choose($event)" title="list-ul">
          <i class="fa fa-list-ul"></i>
        </li>

        <li data-font="location-arrow" data-toggle="tooltip" @click="choose($event)" title="location-arrow">
          <i class="fa fa-location-arrow"></i>
        </li>

        <li data-font="lock" data-toggle="tooltip" @click="choose($event)" title="lock">
          <i class="fa fa-lock"></i>
        </li>

        <li data-font="long-arrow-down" data-toggle="tooltip" @click="choose($event)" title="long-arrow-down">
          <i class="fa fa-long-arrow-down"></i>
        </li>

        <li data-font="long-arrow-left" data-toggle="tooltip" @click="choose($event)" title="long-arrow-left">
          <i class="fa fa-long-arrow-left"></i>
        </li>

        <li data-font="long-arrow-right" data-toggle="tooltip" @click="choose($event)" title="long-arrow-right">
          <i class="fa fa-long-arrow-right"></i>
        </li>

        <li data-font="long-arrow-up" data-toggle="tooltip" @click="choose($event)" title="long-arrow-up">
          <i class="fa fa-long-arrow-up"></i>
        </li>

        <li data-font="low-vision" data-toggle="tooltip" @click="choose($event)" title="low-vision">
          <i class="fa fa-low-vision"></i>
        </li>

        <li data-font="magic" data-toggle="tooltip" @click="choose($event)" title="magic">
          <i class="fa fa-magic"></i>
        </li>

        <li data-font="magnet" data-toggle="tooltip" @click="choose($event)" title="magnet">
          <i class="fa fa-magnet"></i>
        </li>

        <li data-font="mail-forward" data-toggle="tooltip" @click="choose($event)" title="mail-forward">
          <i class="fa fa-mail-forward"></i>
        </li>

        <li data-font="mail-reply" data-toggle="tooltip" @click="choose($event)" title="mail-reply">
          <i class="fa fa-mail-reply"></i>
        </li>

        <li data-font="mail-reply-all" data-toggle="tooltip" @click="choose($event)" title="mail-reply-all">
          <i class="fa fa-mail-reply-all"></i>
        </li>

        <li data-font="male" data-toggle="tooltip" @click="choose($event)" title="male">
          <i class="fa fa-male"></i>
        </li>

        <li data-font="map" data-toggle="tooltip" @click="choose($event)" title="map">
          <i class="fa fa-map"></i>
        </li>

        <li data-font="map-marker" data-toggle="tooltip" @click="choose($event)" title="map-marker">
          <i class="fa fa-map-marker"></i>
        </li>

        <li data-font="map-o" data-toggle="tooltip" @click="choose($event)" title="map-o">
          <i class="fa fa-map-o"></i>
        </li>

        <li data-font="map-pin" data-toggle="tooltip" @click="choose($event)" title="map-pin">
          <i class="fa fa-map-pin"></i>
        </li>

        <li data-font="map-signs" data-toggle="tooltip" @click="choose($event)" title="map-signs">
          <i class="fa fa-map-signs"></i>
        </li>

        <li data-font="mars" data-toggle="tooltip" @click="choose($event)" title="mars">
          <i class="fa fa-mars"></i>
        </li>

        <li data-font="mars-double" data-toggle="tooltip" @click="choose($event)" title="mars-double">
          <i class="fa fa-mars-double"></i>
        </li>

        <li data-font="mars-stroke" data-toggle="tooltip" @click="choose($event)" title="mars-stroke">
          <i class="fa fa-mars-stroke"></i>
        </li>

        <li data-font="mars-stroke-h" data-toggle="tooltip" @click="choose($event)" title="mars-stroke-h">
          <i class="fa fa-mars-stroke-h"></i>
        </li>

        <li data-font="mars-stroke-v" data-toggle="tooltip" @click="choose($event)" title="mars-stroke-v">
          <i class="fa fa-mars-stroke-v"></i>
        </li>

        <li data-font="maxcdn" data-toggle="tooltip" @click="choose($event)" title="maxcdn">
          <i class="fa fa-maxcdn"></i>
        </li>

        <li data-font="meanpath" data-toggle="tooltip" @click="choose($event)" title="meanpath">
          <i class="fa fa-meanpath"></i>
        </li>

        <li data-font="medium" data-toggle="tooltip" @click="choose($event)" title="medium">
          <i class="fa fa-medium"></i>
        </li>

        <li data-font="medkit" data-toggle="tooltip" @click="choose($event)" title="medkit">
          <i class="fa fa-medkit"></i>
        </li>

        <li data-font="meetup" data-toggle="tooltip" @click="choose($event)" title="meetup">
          <i class="fa fa-meetup"></i>
        </li>

        <li data-font="meh-o" data-toggle="tooltip" @click="choose($event)" title="meh-o">
          <i class="fa fa-meh-o"></i>
        </li>

        <li data-font="mercury" data-toggle="tooltip" @click="choose($event)" title="mercury">
          <i class="fa fa-mercury"></i>
        </li>

        <li data-font="microchip" data-toggle="tooltip" @click="choose($event)" title="microchip">
          <i class="fa fa-microchip"></i>
        </li>

        <li data-font="microphone" data-toggle="tooltip" @click="choose($event)" title="microphone">
          <i class="fa fa-microphone"></i>
        </li>

        <li data-font="microphone-slash" data-toggle="tooltip" @click="choose($event)" title="microphone-slash">
          <i class="fa fa-microphone-slash"></i>
        </li>

        <li data-font="minus" data-toggle="tooltip" @click="choose($event)" title="minus">
          <i class="fa fa-minus"></i>
        </li>

        <li data-font="minus-circle" data-toggle="tooltip" @click="choose($event)" title="minus-circle">
          <i class="fa fa-minus-circle"></i>
        </li>

        <li data-font="minus-square" data-toggle="tooltip" @click="choose($event)" title="minus-square">
          <i class="fa fa-minus-square"></i>
        </li>

        <li data-font="minus-square-o" data-toggle="tooltip" @click="choose($event)" title="minus-square-o">
          <i class="fa fa-minus-square-o"></i>
        </li>

        <li data-font="mixcloud" data-toggle="tooltip" @click="choose($event)" title="mixcloud">
          <i class="fa fa-mixcloud"></i>
        </li>

        <li data-font="mobile" data-toggle="tooltip" @click="choose($event)" title="mobile">
          <i class="fa fa-mobile"></i>
        </li>

        <li data-font="mobile-phone" data-toggle="tooltip" @click="choose($event)" title="mobile-phone">
          <i class="fa fa-mobile-phone"></i>
        </li>

        <li data-font="modx" data-toggle="tooltip" @click="choose($event)" title="modx">
          <i class="fa fa-modx"></i>
        </li>

        <li data-font="money" data-toggle="tooltip" @click="choose($event)" title="money">
          <i class="fa fa-money"></i>
        </li>

        <li data-font="moon-o" data-toggle="tooltip" @click="choose($event)" title="moon-o">
          <i class="fa fa-moon-o"></i>
        </li>

        <li data-font="mortar-board" data-toggle="tooltip" @click="choose($event)" title="mortar-board">
          <i class="fa fa-mortar-board"></i>
        </li>

        <li data-font="motorcycle" data-toggle="tooltip" @click="choose($event)" title="motorcycle">
          <i class="fa fa-motorcycle"></i>
        </li>

        <li data-font="mouse-pointer" data-toggle="tooltip" @click="choose($event)" title="mouse-pointer">
          <i class="fa fa-mouse-pointer"></i>
        </li>

        <li data-font="music" data-toggle="tooltip" @click="choose($event)" title="music">
          <i class="fa fa-music"></i>
        </li>

        <li data-font="navicon" data-toggle="tooltip" @click="choose($event)" title="navicon">
          <i class="fa fa-navicon"></i>
        </li>

        <li data-font="neuter" data-toggle="tooltip" @click="choose($event)" title="neuter">
          <i class="fa fa-neuter"></i>
        </li>

        <li data-font="newspaper-o" data-toggle="tooltip" @click="choose($event)" title="newspaper-o">
          <i class="fa fa-newspaper-o"></i>
        </li>

        <li data-font="object-group" data-toggle="tooltip" @click="choose($event)" title="object-group">
          <i class="fa fa-object-group"></i>
        </li>

        <li data-font="object-ungroup" data-toggle="tooltip" @click="choose($event)" title="object-ungroup">
          <i class="fa fa-object-ungroup"></i>
        </li>

        <li data-font="odnoklassniki" data-toggle="tooltip" @click="choose($event)" title="odnoklassniki">
          <i class="fa fa-odnoklassniki"></i>
        </li>

        <li data-font="odnoklassniki-square" data-toggle="tooltip" @click="choose($event)" title="odnoklassniki-square">
          <i class="fa fa-odnoklassniki-square"></i>
        </li>

        <li data-font="opencart" data-toggle="tooltip" @click="choose($event)" title="opencart">
          <i class="fa fa-opencart"></i>
        </li>

        <li data-font="openid" data-toggle="tooltip" @click="choose($event)" title="openid">
          <i class="fa fa-openid"></i>
        </li>

        <li data-font="opera" data-toggle="tooltip" @click="choose($event)" title="opera">
          <i class="fa fa-opera"></i>
        </li>

        <li data-font="optin-monster" data-toggle="tooltip" @click="choose($event)" title="optin-monster">
          <i class="fa fa-optin-monster"></i>
        </li>

        <li data-font="outdent" data-toggle="tooltip" @click="choose($event)" title="outdent">
          <i class="fa fa-outdent"></i>
        </li>

        <li data-font="pagelines" data-toggle="tooltip" @click="choose($event)" title="pagelines">
          <i class="fa fa-pagelines"></i>
        </li>

        <li data-font="paint-brush" data-toggle="tooltip" @click="choose($event)" title="paint-brush">
          <i class="fa fa-paint-brush"></i>
        </li>

        <li data-font="paper-plane" data-toggle="tooltip" @click="choose($event)" title="paper-plane">
          <i class="fa fa-paper-plane"></i>
        </li>

        <li data-font="paper-plane-o" data-toggle="tooltip" @click="choose($event)" title="paper-plane-o">
          <i class="fa fa-paper-plane-o"></i>
        </li>

        <li data-font="paperclip" data-toggle="tooltip" @click="choose($event)" title="paperclip">
          <i class="fa fa-paperclip"></i>
        </li>

        <li data-font="paragraph" data-toggle="tooltip" @click="choose($event)" title="paragraph">
          <i class="fa fa-paragraph"></i>
        </li>

        <li data-font="paste" data-toggle="tooltip" @click="choose($event)" title="paste">
          <i class="fa fa-paste"></i>
        </li>

        <li data-font="pause" data-toggle="tooltip" @click="choose($event)" title="pause">
          <i class="fa fa-pause"></i>
        </li>

        <li data-font="pause-circle" data-toggle="tooltip" @click="choose($event)" title="pause-circle">
          <i class="fa fa-pause-circle"></i>
        </li>

        <li data-font="pause-circle-o" data-toggle="tooltip" @click="choose($event)" title="pause-circle-o">
          <i class="fa fa-pause-circle-o"></i>
        </li>

        <li data-font="paw" data-toggle="tooltip" @click="choose($event)" title="paw">
          <i class="fa fa-paw"></i>
        </li>

        <li data-font="paypal" data-toggle="tooltip" @click="choose($event)" title="paypal">
          <i class="fa fa-paypal"></i>
        </li>

        <li data-font="pencil" data-toggle="tooltip" @click="choose($event)" title="pencil">
          <i class="fa fa-pencil"></i>
        </li>

        <li data-font="pencil-square" data-toggle="tooltip" @click="choose($event)" title="pencil-square">
          <i class="fa fa-pencil-square"></i>
        </li>

        <li data-font="pencil-square-o" data-toggle="tooltip" @click="choose($event)" title="pencil-square-o">
          <i class="fa fa-pencil-square-o"></i>
        </li>

        <li data-font="percent" data-toggle="tooltip" @click="choose($event)" title="percent">
          <i class="fa fa-percent"></i>
        </li>

        <li data-font="phone" data-toggle="tooltip" @click="choose($event)" title="phone">
          <i class="fa fa-phone"></i>
        </li>

        <li data-font="phone-square" data-toggle="tooltip" @click="choose($event)" title="phone-square">
          <i class="fa fa-phone-square"></i>
        </li>

        <li data-font="photo" data-toggle="tooltip" @click="choose($event)" title="photo">
          <i class="fa fa-photo"></i>
        </li>

        <li data-font="picture-o" data-toggle="tooltip" @click="choose($event)" title="picture-o">
          <i class="fa fa-picture-o"></i>
        </li>

        <li data-font="pie-chart" data-toggle="tooltip" @click="choose($event)" title="pie-chart">
          <i class="fa fa-pie-chart"></i>
        </li>

        <li data-font="pied-piper" data-toggle="tooltip" @click="choose($event)" title="pied-piper">
          <i class="fa fa-pied-piper"></i>
        </li>

        <li data-font="pied-piper-alt" data-toggle="tooltip" @click="choose($event)" title="pied-piper-alt">
          <i class="fa fa-pied-piper-alt"></i>
        </li>

        <li data-font="pied-piper-pp" data-toggle="tooltip" @click="choose($event)" title="pied-piper-pp">
          <i class="fa fa-pied-piper-pp"></i>
        </li>

        <li data-font="pinterest" data-toggle="tooltip" @click="choose($event)" title="pinterest">
          <i class="fa fa-pinterest"></i>
        </li>

        <li data-font="pinterest-p" data-toggle="tooltip" @click="choose($event)" title="pinterest-p">
          <i class="fa fa-pinterest-p"></i>
        </li>

        <li data-font="pinterest-square" data-toggle="tooltip" @click="choose($event)" title="pinterest-square">
          <i class="fa fa-pinterest-square"></i>
        </li>

        <li data-font="plane" data-toggle="tooltip" @click="choose($event)" title="plane">
          <i class="fa fa-plane"></i>
        </li>

        <li data-font="play" data-toggle="tooltip" @click="choose($event)" title="play">
          <i class="fa fa-play"></i>
        </li>

        <li data-font="play-circle" data-toggle="tooltip" @click="choose($event)" title="play-circle">
          <i class="fa fa-play-circle"></i>
        </li>

        <li data-font="play-circle-o" data-toggle="tooltip" @click="choose($event)" title="play-circle-o">
          <i class="fa fa-play-circle-o"></i>
        </li>

        <li data-font="plug" data-toggle="tooltip" @click="choose($event)" title="plug">
          <i class="fa fa-plug"></i>
        </li>

        <li data-font="plus" data-toggle="tooltip" @click="choose($event)" title="plus">
          <i class="fa fa-plus"></i>
        </li>

        <li data-font="plus-circle" data-toggle="tooltip" @click="choose($event)" title="plus-circle">
          <i class="fa fa-plus-circle"></i>
        </li>

        <li data-font="plus-square" data-toggle="tooltip" @click="choose($event)" title="plus-square">
          <i class="fa fa-plus-square"></i>
        </li>

        <li data-font="plus-square-o" data-toggle="tooltip" @click="choose($event)" title="plus-square-o">
          <i class="fa fa-plus-square-o"></i>
        </li>

        <li data-font="podcast" data-toggle="tooltip" @click="choose($event)" title="podcast">
          <i class="fa fa-podcast"></i>
        </li>

        <li data-font="power-off" data-toggle="tooltip" @click="choose($event)" title="power-off">
          <i class="fa fa-power-off"></i>
        </li>

        <li data-font="print" data-toggle="tooltip" @click="choose($event)" title="print">
          <i class="fa fa-print"></i>
        </li>

        <li data-font="product-hunt" data-toggle="tooltip" @click="choose($event)" title="product-hunt">
          <i class="fa fa-product-hunt"></i>
        </li>

        <li data-font="puzzle-piece" data-toggle="tooltip" @click="choose($event)" title="puzzle-piece">
          <i class="fa fa-puzzle-piece"></i>
        </li>

        <li data-font="qq" data-toggle="tooltip" @click="choose($event)" title="qq">
          <i class="fa fa-qq"></i>
        </li>

        <li data-font="qrcode" data-toggle="tooltip" @click="choose($event)" title="qrcode">
          <i class="fa fa-qrcode"></i>
        </li>

        <li data-font="question" data-toggle="tooltip" @click="choose($event)" title="question">
          <i class="fa fa-question"></i>
        </li>

        <li data-font="question-circle" data-toggle="tooltip" @click="choose($event)" title="question-circle">
          <i class="fa fa-question-circle"></i>
        </li>

        <li data-font="question-circle-o" data-toggle="tooltip" @click="choose($event)" title="question-circle-o">
          <i class="fa fa-question-circle-o"></i>
        </li>

        <li data-font="quora" data-toggle="tooltip" @click="choose($event)" title="quora">
          <i class="fa fa-quora"></i>
        </li>

        <li data-font="quote-left" data-toggle="tooltip" @click="choose($event)" title="quote-left">
          <i class="fa fa-quote-left"></i>
        </li>

        <li data-font="quote-right" data-toggle="tooltip" @click="choose($event)" title="quote-right">
          <i class="fa fa-quote-right"></i>
        </li>

        <li data-font="ra" data-toggle="tooltip" @click="choose($event)" title="ra">
          <i class="fa fa-ra"></i>
        </li>

        <li data-font="random" data-toggle="tooltip" @click="choose($event)" title="random">
          <i class="fa fa-random"></i>
        </li>

        <li data-font="ravelry" data-toggle="tooltip" @click="choose($event)" title="ravelry">
          <i class="fa fa-ravelry"></i>
        </li>

        <li data-font="rebel" data-toggle="tooltip" @click="choose($event)" title="rebel">
          <i class="fa fa-rebel"></i>
        </li>

        <li data-font="recycle" data-toggle="tooltip" @click="choose($event)" title="recycle">
          <i class="fa fa-recycle"></i>
        </li>

        <li data-font="reddit" data-toggle="tooltip" @click="choose($event)" title="reddit">
          <i class="fa fa-reddit"></i>
        </li>

        <li data-font="reddit-alien" data-toggle="tooltip" @click="choose($event)" title="reddit-alien">
          <i class="fa fa-reddit-alien"></i>
        </li>

        <li data-font="reddit-square" data-toggle="tooltip" @click="choose($event)" title="reddit-square">
          <i class="fa fa-reddit-square"></i>
        </li>

        <li data-font="refresh" data-toggle="tooltip" @click="choose($event)" title="refresh">
          <i class="fa fa-refresh"></i>
        </li>

        <li data-font="registered" data-toggle="tooltip" @click="choose($event)" title="registered">
          <i class="fa fa-registered"></i>
        </li>

        <li data-font="remove" data-toggle="tooltip" @click="choose($event)" title="remove">
          <i class="fa fa-remove"></i>
        </li>

        <li data-font="renren" data-toggle="tooltip" @click="choose($event)" title="renren">
          <i class="fa fa-renren"></i>
        </li>

        <li data-font="reorder" data-toggle="tooltip" @click="choose($event)" title="reorder">
          <i class="fa fa-reorder"></i>
        </li>

        <li data-font="repeat" data-toggle="tooltip" @click="choose($event)" title="repeat">
          <i class="fa fa-repeat"></i>
        </li>

        <li data-font="reply" data-toggle="tooltip" @click="choose($event)" title="reply">
          <i class="fa fa-reply"></i>
        </li>

        <li data-font="reply-all" data-toggle="tooltip" @click="choose($event)" title="reply-all">
          <i class="fa fa-reply-all"></i>
        </li>

        <li data-font="resistance" data-toggle="tooltip" @click="choose($event)" title="resistance">
          <i class="fa fa-resistance"></i>
        </li>

        <li data-font="retweet" data-toggle="tooltip" @click="choose($event)" title="retweet">
          <i class="fa fa-retweet"></i>
        </li>

        <li data-font="rmb" data-toggle="tooltip" @click="choose($event)" title="rmb">
          <i class="fa fa-rmb"></i>
        </li>

        <li data-font="road" data-toggle="tooltip" @click="choose($event)" title="road">
          <i class="fa fa-road"></i>
        </li>

        <li data-font="rocket" data-toggle="tooltip" @click="choose($event)" title="rocket">
          <i class="fa fa-rocket"></i>
        </li>

        <li data-font="rotate-left" data-toggle="tooltip" @click="choose($event)" title="rotate-left">
          <i class="fa fa-rotate-left"></i>
        </li>

        <li data-font="rotate-right" data-toggle="tooltip" @click="choose($event)" title="rotate-right">
          <i class="fa fa-rotate-right"></i>
        </li>

        <li data-font="rouble" data-toggle="tooltip" @click="choose($event)" title="rouble">
          <i class="fa fa-rouble"></i>
        </li>

        <li data-font="rss" data-toggle="tooltip" @click="choose($event)" title="rss">
          <i class="fa fa-rss"></i>
        </li>

        <li data-font="rss-square" data-toggle="tooltip" @click="choose($event)" title="rss-square">
          <i class="fa fa-rss-square"></i>
        </li>

        <li data-font="rub" data-toggle="tooltip" @click="choose($event)" title="rub">
          <i class="fa fa-rub"></i>
        </li>

        <li data-font="ruble" data-toggle="tooltip" @click="choose($event)" title="ruble">
          <i class="fa fa-ruble"></i>
        </li>

        <li data-font="rupee" data-toggle="tooltip" @click="choose($event)" title="rupee">
          <i class="fa fa-rupee"></i>
        </li>

        <li data-font="s15" data-toggle="tooltip" @click="choose($event)" title="s15">
          <i class="fa fa-s15"></i>
        </li>

        <li data-font="safari" data-toggle="tooltip" @click="choose($event)" title="safari">
          <i class="fa fa-safari"></i>
        </li>

        <li data-font="save" data-toggle="tooltip" @click="choose($event)" title="save">
          <i class="fa fa-save"></i>
        </li>

        <li data-font="scissors" data-toggle="tooltip" @click="choose($event)" title="scissors">
          <i class="fa fa-scissors"></i>
        </li>

        <li data-font="scribd" data-toggle="tooltip" @click="choose($event)" title="scribd">
          <i class="fa fa-scribd"></i>
        </li>

        <li data-font="search" data-toggle="tooltip" @click="choose($event)" title="search">
          <i class="fa fa-search"></i>
        </li>

        <li data-font="search-minus" data-toggle="tooltip" @click="choose($event)" title="search-minus">
          <i class="fa fa-search-minus"></i>
        </li>

        <li data-font="search-plus" data-toggle="tooltip" @click="choose($event)" title="search-plus">
          <i class="fa fa-search-plus"></i>
        </li>

        <li data-font="sellsy" data-toggle="tooltip" @click="choose($event)" title="sellsy">
          <i class="fa fa-sellsy"></i>
        </li>

        <li data-font="send" data-toggle="tooltip" @click="choose($event)" title="send">
          <i class="fa fa-send"></i>
        </li>

        <li data-font="send-o" data-toggle="tooltip" @click="choose($event)" title="send-o">
          <i class="fa fa-send-o"></i>
        </li>

        <li data-font="server" data-toggle="tooltip" @click="choose($event)" title="server">
          <i class="fa fa-server"></i>
        </li>

        <li data-font="share" data-toggle="tooltip" @click="choose($event)" title="share">
          <i class="fa fa-share"></i>
        </li>

        <li data-font="share-alt" data-toggle="tooltip" @click="choose($event)" title="share-alt">
          <i class="fa fa-share-alt"></i>
        </li>

        <li data-font="share-alt-square" data-toggle="tooltip" @click="choose($event)" title="share-alt-square">
          <i class="fa fa-share-alt-square"></i>
        </li>

        <li data-font="share-square" data-toggle="tooltip" @click="choose($event)" title="share-square">
          <i class="fa fa-share-square"></i>
        </li>

        <li data-font="share-square-o" data-toggle="tooltip" @click="choose($event)" title="share-square-o">
          <i class="fa fa-share-square-o"></i>
        </li>

        <li data-font="shekel" data-toggle="tooltip" @click="choose($event)" title="shekel">
          <i class="fa fa-shekel"></i>
        </li>

        <li data-font="sheqel" data-toggle="tooltip" @click="choose($event)" title="sheqel">
          <i class="fa fa-sheqel"></i>
        </li>

        <li data-font="shield" data-toggle="tooltip" @click="choose($event)" title="shield">
          <i class="fa fa-shield"></i>
        </li>

        <li data-font="ship" data-toggle="tooltip" @click="choose($event)" title="ship">
          <i class="fa fa-ship"></i>
        </li>

        <li data-font="shirtsinbulk" data-toggle="tooltip" @click="choose($event)" title="shirtsinbulk">
          <i class="fa fa-shirtsinbulk"></i>
        </li>

        <li data-font="shopping-bag" data-toggle="tooltip" @click="choose($event)" title="shopping-bag">
          <i class="fa fa-shopping-bag"></i>
        </li>

        <li data-font="shopping-basket" data-toggle="tooltip" @click="choose($event)" title="shopping-basket">
          <i class="fa fa-shopping-basket"></i>
        </li>

        <li data-font="shopping-cart" data-toggle="tooltip" @click="choose($event)" title="shopping-cart">
          <i class="fa fa-shopping-cart"></i>
        </li>

        <li data-font="shower" data-toggle="tooltip" @click="choose($event)" title="shower">
          <i class="fa fa-shower"></i>
        </li>

        <li data-font="sign-in" data-toggle="tooltip" @click="choose($event)" title="sign-in">
          <i class="fa fa-sign-in"></i>
        </li>

        <li data-font="sign-language" data-toggle="tooltip" @click="choose($event)" title="sign-language">
          <i class="fa fa-sign-language"></i>
        </li>

        <li data-font="sign-out" data-toggle="tooltip" @click="choose($event)" title="sign-out">
          <i class="fa fa-sign-out"></i>
        </li>

        <li data-font="signal" data-toggle="tooltip" @click="choose($event)" title="signal">
          <i class="fa fa-signal"></i>
        </li>

        <li data-font="signing" data-toggle="tooltip" @click="choose($event)" title="signing">
          <i class="fa fa-signing"></i>
        </li>

        <li data-font="simplybuilt" data-toggle="tooltip" @click="choose($event)" title="simplybuilt">
          <i class="fa fa-simplybuilt"></i>
        </li>

        <li data-font="sitemap" data-toggle="tooltip" @click="choose($event)" title="sitemap">
          <i class="fa fa-sitemap"></i>
        </li>

        <li data-font="skyatlas" data-toggle="tooltip" @click="choose($event)" title="skyatlas">
          <i class="fa fa-skyatlas"></i>
        </li>

        <li data-font="skype" data-toggle="tooltip" @click="choose($event)" title="skype">
          <i class="fa fa-skype"></i>
        </li>

        <li data-font="slack" data-toggle="tooltip" @click="choose($event)" title="slack">
          <i class="fa fa-slack"></i>
        </li>

        <li data-font="sliders" data-toggle="tooltip" @click="choose($event)" title="sliders">
          <i class="fa fa-sliders"></i>
        </li>

        <li data-font="slideshare" data-toggle="tooltip" @click="choose($event)" title="slideshare">
          <i class="fa fa-slideshare"></i>
        </li>

        <li data-font="smile-o" data-toggle="tooltip" @click="choose($event)" title="smile-o">
          <i class="fa fa-smile-o"></i>
        </li>

        <li data-font="snapchat" data-toggle="tooltip" @click="choose($event)" title="snapchat">
          <i class="fa fa-snapchat"></i>
        </li>

        <li data-font="snapchat-ghost" data-toggle="tooltip" @click="choose($event)" title="snapchat-ghost">
          <i class="fa fa-snapchat-ghost"></i>
        </li>

        <li data-font="snapchat-square" data-toggle="tooltip" @click="choose($event)" title="snapchat-square">
          <i class="fa fa-snapchat-square"></i>
        </li>

        <li data-font="snowflake-o" data-toggle="tooltip" @click="choose($event)" title="snowflake-o">
          <i class="fa fa-snowflake-o"></i>
        </li>

        <li data-font="soccer-ball-o" data-toggle="tooltip" @click="choose($event)" title="soccer-ball-o">
          <i class="fa fa-soccer-ball-o"></i>
        </li>

        <li data-font="sort" data-toggle="tooltip" @click="choose($event)" title="sort">
          <i class="fa fa-sort"></i>
        </li>

        <li data-font="sort-alpha-asc" data-toggle="tooltip" @click="choose($event)" title="sort-alpha-asc">
          <i class="fa fa-sort-alpha-asc"></i>
        </li>

        <li data-font="sort-alpha-desc" data-toggle="tooltip" @click="choose($event)" title="sort-alpha-desc">
          <i class="fa fa-sort-alpha-desc"></i>
        </li>

        <li data-font="sort-amount-asc" data-toggle="tooltip" @click="choose($event)" title="sort-amount-asc">
          <i class="fa fa-sort-amount-asc"></i>
        </li>

        <li data-font="sort-amount-desc" data-toggle="tooltip" @click="choose($event)" title="sort-amount-desc">
          <i class="fa fa-sort-amount-desc"></i>
        </li>

        <li data-font="sort-asc" data-toggle="tooltip" @click="choose($event)" title="sort-asc">
          <i class="fa fa-sort-asc"></i>
        </li>

        <li data-font="sort-desc" data-toggle="tooltip" @click="choose($event)" title="sort-desc">
          <i class="fa fa-sort-desc"></i>
        </li>
        <li data-font="sort-down" data-toggle="tooltip" @click="choose($event)" title="sort-down">
          <i class="fa fa-sort-down"></i>
        </li>
        <li data-font="sort-numeric-asc" data-toggle="tooltip" @click="choose($event)" title="sort-numeric-asc">
          <i class="fa fa-sort-numeric-asc"></i>
        </li>
        <li data-font="sort-numeric-desc" data-toggle="tooltip" @click="choose($event)" title="sort-numeric-desc">
          <i class="fa fa-sort-numeric-desc"></i>
        </li>
        <li data-font="sort-up" data-toggle="tooltip" @click="choose($event)" title="sort-up">
          <i class="fa fa-sort-up"></i>
        </li>
        <li data-font="soundcloud" data-toggle="tooltip" @click="choose($event)" title="soundcloud">
          <i class="fa fa-soundcloud"></i>
        </li>
        <li data-font="space-shuttle" data-toggle="tooltip" @click="choose($event)" title="space-shuttle">
          <i class="fa fa-space-shuttle"></i>
        </li>
        <li data-font="spinner" data-toggle="tooltip" @click="choose($event)" title="spinner">
          <i class="fa fa-spinner"></i>
        </li>
        <li data-font="spoon" data-toggle="tooltip" @click="choose($event)" title="spoon">
          <i class="fa fa-spoon"></i>
        </li>
        <li data-font="spotify" data-toggle="tooltip" @click="choose($event)" title="spotify">
          <i class="fa fa-spotify"></i>
        </li>
        <li data-font="square" data-toggle="tooltip" @click="choose($event)" title="square">
          <i class="fa fa-square"></i>
        </li>
        <li data-font="square-o" data-toggle="tooltip" @click="choose($event)" title="square-o">
          <i class="fa fa-square-o"></i>
        </li>
        <li data-font="stack-exchange" data-toggle="tooltip" @click="choose($event)" title="stack-exchange">
          <i class="fa fa-stack-exchange"></i>
        </li>
        <li data-font="stack-overflow" data-toggle="tooltip" @click="choose($event)" title="stack-overflow">
          <i class="fa fa-stack-overflow"></i>
        </li>
        <li data-font="star" data-toggle="tooltip" @click="choose($event)" title="star">
          <i class="fa fa-star"></i>
        </li>
        <li data-font="star-half" data-toggle="tooltip" @click="choose($event)" title="star-half">
          <i class="fa fa-star-half"></i>
        </li>
        <li data-font="star-half-empty" data-toggle="tooltip" @click="choose($event)" title="star-half-empty">
          <i class="fa fa-star-half-empty"></i>
        </li>
        <li data-font="star-half-full" data-toggle="tooltip" @click="choose($event)" title="star-half-full">
          <i class="fa fa-star-half-full"></i>
        </li>
        <li data-font="star-half-o" data-toggle="tooltip" @click="choose($event)" title="star-half-o">
          <i class="fa fa-star-half-o"></i>
        </li>
        <li data-font="star-o" data-toggle="tooltip" @click="choose($event)" title="star-o">
          <i class="fa fa-star-o"></i>
        </li>
        <li data-font="steam" data-toggle="tooltip" @click="choose($event)" title="steam">
          <i class="fa fa-steam"></i>
        </li>
        <li data-font="steam-square" data-toggle="tooltip" @click="choose($event)" title="steam-square">
          <i class="fa fa-steam-square"></i>
        </li>
        <li data-font="step-backward" data-toggle="tooltip" @click="choose($event)" title="step-backward">
          <i class="fa fa-step-backward"></i>
        </li>
        <li data-font="step-forward" data-toggle="tooltip" @click="choose($event)" title="step-forward">
          <i class="fa fa-step-forward"></i>
        </li>
        <li data-font="stethoscope" data-toggle="tooltip" @click="choose($event)" title="stethoscope">
          <i class="fa fa-stethoscope"></i>
        </li>
        <li data-font="sticky-note" data-toggle="tooltip" @click="choose($event)" title="sticky-note">
          <i class="fa fa-sticky-note"></i>
        </li>
        <li data-font="sticky-note-o" data-toggle="tooltip" @click="choose($event)" title="sticky-note-o">
          <i class="fa fa-sticky-note-o"></i>
        </li>
        <li data-font="stop" data-toggle="tooltip" @click="choose($event)" title="stop">
          <i class="fa fa-stop"></i>
        </li>
        <li data-font="stop-circle" data-toggle="tooltip" @click="choose($event)" title="stop-circle">
          <i class="fa fa-stop-circle"></i>
        </li>
        <li data-font="stop-circle-o" data-toggle="tooltip" @click="choose($event)" title="stop-circle-o">
          <i class="fa fa-stop-circle-o"></i>
        </li>
        <li data-font="street-view" data-toggle="tooltip" @click="choose($event)" title="street-view">
          <i class="fa fa-street-view"></i>
        </li>
        <li data-font="strikethrough" data-toggle="tooltip" @click="choose($event)" title="strikethrough">
          <i class="fa fa-strikethrough"></i>
        </li>
        <li data-font="stumbleupon" data-toggle="tooltip" @click="choose($event)" title="stumbleupon">
          <i class="fa fa-stumbleupon"></i>
        </li>
        <li data-font="stumbleupon-circle" data-toggle="tooltip" @click="choose($event)" title="stumbleupon-circle">
          <i class="fa fa-stumbleupon-circle"></i>
        </li>
        <li data-font="subscript" data-toggle="tooltip" @click="choose($event)" title="subscript">
          <i class="fa fa-subscript"></i>
        </li>
        <li data-font="subway" data-toggle="tooltip" @click="choose($event)" title="subway">
          <i class="fa fa-subway"></i>
        </li>
        <li data-font="suitcase" data-toggle="tooltip" @click="choose($event)" title="suitcase">
          <i class="fa fa-suitcase"></i>
        </li>
        <li data-font="sun-o" data-toggle="tooltip" @click="choose($event)" title="sun-o">
          <i class="fa fa-sun-o"></i>
        </li>
        <li data-font="superpowers" data-toggle="tooltip" @click="choose($event)" title="superpowers">
          <i class="fa fa-superpowers"></i>
        </li>
        <li data-font="superscript" data-toggle="tooltip" @click="choose($event)" title="superscript">
          <i class="fa fa-superscript"></i>
        </li>
        <li data-font="support" data-toggle="tooltip" @click="choose($event)" title="support">
          <i class="fa fa-support"></i>
        </li>
        <li data-font="table" data-toggle="tooltip" @click="choose($event)" title="table">
          <i class="fa fa-table"></i>
        </li>
        <li data-font="tablet" data-toggle="tooltip" @click="choose($event)" title="tablet">
          <i class="fa fa-tablet"></i>
        </li>
        <li data-font="tachometer" data-toggle="tooltip" @click="choose($event)" title="tachometer">
          <i class="fa fa-tachometer"></i>
        </li>
        <li data-font="tag" data-toggle="tooltip" @click="choose($event)" title="tag">
          <i class="fa fa-tag"></i>
        </li>
        <li data-font="tags" data-toggle="tooltip" @click="choose($event)" title="tags">
          <i class="fa fa-tags"></i>
        </li>
        <li data-font="tasks" data-toggle="tooltip" @click="choose($event)" title="tasks">
          <i class="fa fa-tasks"></i>
        </li>
        <li data-font="taxi" data-toggle="tooltip" @click="choose($event)" title="taxi">
          <i class="fa fa-taxi"></i>
        </li>
        <li data-font="telegram" data-toggle="tooltip" @click="choose($event)" title="telegram">
          <i class="fa fa-telegram"></i>
        </li>
        <li data-font="television" data-toggle="tooltip" @click="choose($event)" title="television">
          <i class="fa fa-television"></i>
        </li>
        <li data-font="tencent-weibo" data-toggle="tooltip" @click="choose($event)" title="tencent-weibo">
          <i class="fa fa-tencent-weibo"></i>
        </li>
        <li data-font="terminal" data-toggle="tooltip" @click="choose($event)" title="terminal">
          <i class="fa fa-terminal"></i>
        </li>
        <li data-font="text-height" data-toggle="tooltip" @click="choose($event)" title="text-height">
          <i class="fa fa-text-height"></i>
        </li>
        <li data-font="text-width" data-toggle="tooltip" @click="choose($event)" title="text-width">
          <i class="fa fa-text-width"></i>
        </li>
        <li data-font="th" data-toggle="tooltip" @click="choose($event)" title="th">
          <i class="fa fa-th"></i>
        </li>
        <li data-font="th-large" data-toggle="tooltip" @click="choose($event)" title="th-large">
          <i class="fa fa-th-large"></i>
        </li>
        <li data-font="th-list" data-toggle="tooltip" @click="choose($event)" title="th-list">
          <i class="fa fa-th-list"></i>
        </li>
        <li data-font="themeisle" data-toggle="tooltip" @click="choose($event)" title="themeisle">
          <i class="fa fa-themeisle"></i>
        </li>
        <li data-font="thermometer" data-toggle="tooltip" @click="choose($event)" title="thermometer">
          <i class="fa fa-thermometer"></i>
        </li>
        <li data-font="thermometer-0" data-toggle="tooltip" @click="choose($event)" title="thermometer-0">
          <i class="fa fa-thermometer-0"></i>
        </li>
        <li data-font="thermometer-1" data-toggle="tooltip" @click="choose($event)" title="thermometer-1">
          <i class="fa fa-thermometer-1"></i>
        </li>
        <li data-font="thermometer-2" data-toggle="tooltip" @click="choose($event)" title="thermometer-2">
          <i class="fa fa-thermometer-2"></i>
        </li>
        <li data-font="thermometer-3" data-toggle="tooltip" @click="choose($event)" title="thermometer-3">
          <i class="fa fa-thermometer-3"></i>
        </li>
        <li data-font="thermometer-4" data-toggle="tooltip" @click="choose($event)" title="thermometer-4">
          <i class="fa fa-thermometer-4"></i>
        </li>
        <li data-font="thermometer-empty" data-toggle="tooltip" @click="choose($event)" title="thermometer-empty">
          <i class="fa fa-thermometer-empty"></i>
        </li>
        <li data-font="thermometer-full" data-toggle="tooltip" @click="choose($event)" title="thermometer-full">
          <i class="fa fa-thermometer-full"></i>
        </li>
        <li data-font="thermometer-half" data-toggle="tooltip" @click="choose($event)" title="thermometer-half">
          <i class="fa fa-thermometer-half"></i>
        </li>
        <li data-font="thermometer-quarter" data-toggle="tooltip" @click="choose($event)" title="thermometer-quarter">
          <i class="fa fa-thermometer-quarter"></i>
        </li>
        <li data-font="thermometer-three-quarters" data-toggle="tooltip" @click="choose($event)"
            title="thermometer-three-quarters">
          <i class="fa fa-thermometer-three-quarters"></i>
        </li>
        <li data-font="thumb-tack" data-toggle="tooltip" @click="choose($event)" title="thumb-tack">
          <i class="fa fa-thumb-tack"></i>
        </li>
        <li data-font="thumbs-down" data-toggle="tooltip" @click="choose($event)" title="thumbs-down">
          <i class="fa fa-thumbs-down"></i>
        </li>
        <li data-font="thumbs-o-down" data-toggle="tooltip" @click="choose($event)" title="thumbs-o-down">
          <i class="fa fa-thumbs-o-down"></i>
        </li>
        <li data-font="thumbs-o-up" data-toggle="tooltip" @click="choose($event)" title="thumbs-o-up">
          <i class="fa fa-thumbs-o-up"></i>
        </li>
        <li data-font="thumbs-up" data-toggle="tooltip" @click="choose($event)" title="thumbs-up">
          <i class="fa fa-thumbs-up"></i>
        </li>
        <li data-font="ticket" data-toggle="tooltip" @click="choose($event)" title="ticket">
          <i class="fa fa-ticket"></i>
        </li>
        <li data-font="times" data-toggle="tooltip" @click="choose($event)" title="times">
          <i class="fa fa-times"></i>
        </li>
        <li data-font="times-circle" data-toggle="tooltip" @click="choose($event)" title="times-circle">
          <i class="fa fa-times-circle"></i>
        </li>
        <li data-font="times-circle-o" data-toggle="tooltip" @click="choose($event)" title="times-circle-o">
          <i class="fa fa-times-circle-o"></i>
        </li>
        <li data-font="times-rectangle" data-toggle="tooltip" @click="choose($event)" title="times-rectangle">
          <i class="fa fa-times-rectangle"></i>
        </li>
        <li data-font="times-rectangle-o" data-toggle="tooltip" @click="choose($event)" title="times-rectangle-o">
          <i class="fa fa-times-rectangle-o"></i>
        </li>
        <li data-font="tint" data-toggle="tooltip" @click="choose($event)" title="tint">
          <i class="fa fa-tint"></i>
        </li>
        <li data-font="toggle-down" data-toggle="tooltip" @click="choose($event)" title="toggle-down">
          <i class="fa fa-toggle-down"></i>
        </li>
        <li data-font="toggle-left" data-toggle="tooltip" @click="choose($event)" title="toggle-left">
          <i class="fa fa-toggle-left"></i>
        </li>
        <li data-font="toggle-off" data-toggle="tooltip" @click="choose($event)" title="toggle-off">
          <i class="fa fa-toggle-off"></i>
        </li>
        <li data-font="toggle-on" data-toggle="tooltip" @click="choose($event)" title="toggle-on">
          <i class="fa fa-toggle-on"></i>
        </li>
        <li data-font="toggle-right" data-toggle="tooltip" @click="choose($event)" title="toggle-right">
          <i class="fa fa-toggle-right"></i>
        </li>
        <li data-font="toggle-up" data-toggle="tooltip" @click="choose($event)" title="toggle-up">
          <i class="fa fa-toggle-up"></i>
        </li>
        <li data-font="trademark" data-toggle="tooltip" @click="choose($event)" title="trademark">
          <i class="fa fa-trademark"></i>
        </li>
        <li data-font="train" data-toggle="tooltip" @click="choose($event)" title="train">
          <i class="fa fa-train"></i>
        </li>
        <li data-font="transgender" data-toggle="tooltip" @click="choose($event)" title="transgender">
          <i class="fa fa-transgender"></i>
        </li>
        <li data-font="transgender-alt" data-toggle="tooltip" @click="choose($event)" title="transgender-alt">
          <i class="fa fa-transgender-alt"></i>
        </li>
        <li data-font="trash" data-toggle="tooltip" @click="choose($event)" title="trash">
          <i class="fa fa-trash"></i>
        </li>
        <li data-font="trash-o" data-toggle="tooltip" @click="choose($event)" title="trash-o">
          <i class="fa fa-trash-o"></i>
        </li>
        <li data-font="tree" data-toggle="tooltip" @click="choose($event)" title="tree">
          <i class="fa fa-tree"></i>
        </li>
        <li data-font="trello" data-toggle="tooltip" @click="choose($event)" title="trello">
          <i class="fa fa-trello"></i>
        </li>
        <li data-font="tripadvisor" data-toggle="tooltip" @click="choose($event)" title="tripadvisor">
          <i class="fa fa-tripadvisor"></i>
        </li>
        <li data-font="trophy" data-toggle="tooltip" @click="choose($event)" title="trophy">
          <i class="fa fa-trophy"></i>
        </li>
        <li data-font="truck" data-toggle="tooltip" @click="choose($event)" title="truck">
          <i class="fa fa-truck"></i>
        </li>
        <li data-font="try" data-toggle="tooltip" @click="choose($event)" title="try">
          <i class="fa fa-try"></i>
        </li>
        <li data-font="tty" data-toggle="tooltip" @click="choose($event)" title="tty">
          <i class="fa fa-tty"></i>
        </li>
        <li data-font="tumblr" data-toggle="tooltip" @click="choose($event)" title="tumblr">
          <i class="fa fa-tumblr"></i>
        </li>
        <li data-font="tumblr-square" data-toggle="tooltip" @click="choose($event)" title="tumblr-square">
          <i class="fa fa-tumblr-square"></i>
        </li>
        <li data-font="turkish-lira" data-toggle="tooltip" @click="choose($event)" title="turkish-lira">
          <i class="fa fa-turkish-lira"></i>
        </li>
        <li data-font="tv" data-toggle="tooltip" @click="choose($event)" title="tv">
          <i class="fa fa-tv"></i>
        </li>
        <li data-font="twitch" data-toggle="tooltip" @click="choose($event)" title="twitch">
          <i class="fa fa-twitch"></i>
        </li>
        <li data-font="twitter" data-toggle="tooltip" @click="choose($event)" title="twitter">
          <i class="fa fa-twitter"></i>
        </li>
        <li data-font="twitter-square" data-toggle="tooltip" @click="choose($event)" title="twitter-square">
          <i class="fa fa-twitter-square"></i>
        </li>
        <li data-font="umbrella" data-toggle="tooltip" @click="choose($event)" title="umbrella">
          <i class="fa fa-umbrella"></i>
        </li>
        <li data-font="underline" data-toggle="tooltip" @click="choose($event)" title="underline">
          <i class="fa fa-underline"></i>
        </li>
        <li data-font="undo" data-toggle="tooltip" @click="choose($event)" title="undo">
          <i class="fa fa-undo"></i>
        </li>
        <li data-font="universal-access" data-toggle="tooltip" @click="choose($event)" title="universal-access">
          <i class="fa fa-universal-access"></i>
        </li>
        <li data-font="university" data-toggle="tooltip" @click="choose($event)" title="university">
          <i class="fa fa-university"></i>
        </li>
        <li data-font="unlink" data-toggle="tooltip" @click="choose($event)" title="unlink">
          <i class="fa fa-unlink"></i>
        </li>
        <li data-font="unlock" data-toggle="tooltip" @click="choose($event)" title="unlock">
          <i class="fa fa-unlock"></i>
        </li>
        <li data-font="unlock-alt" data-toggle="tooltip" @click="choose($event)" title="unlock-alt">
          <i class="fa fa-unlock-alt"></i>
        </li>
        <li data-font="unsorted" data-toggle="tooltip" @click="choose($event)" title="unsorted">
          <i class="fa fa-unsorted"></i>
        </li>
        <li data-font="upload" data-toggle="tooltip" @click="choose($event)" title="upload">
          <i class="fa fa-upload"></i>
        </li>
        <li data-font="usb" data-toggle="tooltip" @click="choose($event)" title="usb">
          <i class="fa fa-usb"></i>
        </li>
        <li data-font="usd" data-toggle="tooltip" @click="choose($event)" title="usd">
          <i class="fa fa-usd"></i>
        </li>
        <li data-font="user" data-toggle="tooltip" @click="choose($event)" title="user">
          <i class="fa fa-user"></i>
        </li>
        <li data-font="user-circle" data-toggle="tooltip" @click="choose($event)" title="user-circle">
          <i class="fa fa-user-circle"></i>
        </li>
        <li data-font="user-circle-o" data-toggle="tooltip" @click="choose($event)" title="user-circle-o">
          <i class="fa fa-user-circle-o"></i>
        </li>
        <li data-font="user-md" data-toggle="tooltip" @click="choose($event)" title="user-md">
          <i class="fa fa-user-md"></i>
        </li>
        <li data-font="user-o" data-toggle="tooltip" @click="choose($event)" title="user-o">
          <i class="fa fa-user-o"></i>
        </li>
        <li data-font="user-plus" data-toggle="tooltip" @click="choose($event)" title="user-plus">
          <i class="fa fa-user-plus"></i>
        </li>
        <li data-font="user-secret" data-toggle="tooltip" @click="choose($event)" title="user-secret">
          <i class="fa fa-user-secret"></i>
        </li>
        <li data-font="user-times" data-toggle="tooltip" @click="choose($event)" title="user-times">
          <i class="fa fa-user-times"></i>
        </li>
        <li data-font="users" data-toggle="tooltip" @click="choose($event)" title="users">
          <i class="fa fa-users"></i>
        </li>
        <li data-font="vcard" data-toggle="tooltip" @click="choose($event)" title="vcard">
          <i class="fa fa-vcard"></i>
        </li>
        <li data-font="vcard-o" data-toggle="tooltip" @click="choose($event)" title="vcard-o">
          <i class="fa fa-vcard-o"></i>
        </li>
        <li data-font="venus" data-toggle="tooltip" @click="choose($event)" title="venus">
          <i class="fa fa-venus"></i>
        </li>
        <li data-font="venus-double" data-toggle="tooltip" @click="choose($event)" title="venus-double">
          <i class="fa fa-venus-double"></i>
        </li>
        <li data-font="venus-mars" data-toggle="tooltip" @click="choose($event)" title="venus-mars">
          <i class="fa fa-venus-mars"></i>
        </li>
        <li data-font="viacoin" data-toggle="tooltip" @click="choose($event)" title="viacoin">
          <i class="fa fa-viacoin"></i>
        </li>
        <li data-font="viadeo" data-toggle="tooltip" @click="choose($event)" title="viadeo">
          <i class="fa fa-viadeo"></i>
        </li>
        <li data-font="viadeo-square" data-toggle="tooltip" @click="choose($event)" title="viadeo-square">
          <i class="fa fa-viadeo-square"></i>
        </li>
        <li data-font="video-camera" data-toggle="tooltip" @click="choose($event)" title="video-camera">
          <i class="fa fa-video-camera"></i>
        </li>
        <li data-font="vimeo" data-toggle="tooltip" @click="choose($event)" title="vimeo">
          <i class="fa fa-vimeo"></i>
        </li>
        <li data-font="vimeo-square" data-toggle="tooltip" @click="choose($event)" title="vimeo-square">
          <i class="fa fa-vimeo-square"></i>
        </li>
        <li data-font="vine" data-toggle="tooltip" @click="choose($event)" title="vine">
          <i class="fa fa-vine"></i>
        </li>
        <li data-font="vk" data-toggle="tooltip" @click="choose($event)" title="vk">
          <i class="fa fa-vk"></i>
        </li>
        <li data-font="volume-control-phone" data-toggle="tooltip" @click="choose($event)" title="volume-control-phone">
          <i class="fa fa-volume-control-phone"></i>
        </li>
        <li data-font="volume-down" data-toggle="tooltip" @click="choose($event)" title="volume-down">
          <i class="fa fa-volume-down"></i>
        </li>
        <li data-font="volume-off" data-toggle="tooltip" @click="choose($event)" title="volume-off">
          <i class="fa fa-volume-off"></i>
        </li>
        <li data-font="volume-up" data-toggle="tooltip" @click="choose($event)" title="volume-up">
          <i class="fa fa-volume-up"></i>
        </li>
        <li data-font="warning" data-toggle="tooltip" @click="choose($event)" title="warning">
          <i class="fa fa-warning"></i>
        </li>
        <li data-font="wechat" data-toggle="tooltip" @click="choose($event)" title="wechat">
          <i class="fa fa-wechat"></i>
        </li>
        <li data-font="weibo" data-toggle="tooltip" @click="choose($event)" title="weibo">
          <i class="fa fa-weibo"></i>
        </li>
        <li data-font="weixin" data-toggle="tooltip" @click="choose($event)" title="weixin">
          <i class="fa fa-weixin"></i>
        </li>
        <li data-font="whatsapp" data-toggle="tooltip" @click="choose($event)" title="whatsapp">
          <i class="fa fa-whatsapp"></i>
        </li>
        <li data-font="wheelchair" data-toggle="tooltip" @click="choose($event)" title="wheelchair">
          <i class="fa fa-wheelchair"></i>
        </li>
        <li data-font="wheelchair-alt" data-toggle="tooltip" @click="choose($event)" title="wheelchair-alt">
          <i class="fa fa-wheelchair-alt"></i>
        </li>
        <li data-font="wifi" data-toggle="tooltip" @click="choose($event)" title="wifi">
          <i class="fa fa-wifi"></i>
        </li>
        <li data-font="wikipedia-w" data-toggle="tooltip" @click="choose($event)" title="wikipedia-w">
          <i class="fa fa-wikipedia-w"></i>
        </li>
        <li data-font="window-close" data-toggle="tooltip" @click="choose($event)" title="window-close">
          <i class="fa fa-window-close"></i>
        </li>
        <li data-font="window-close-o" data-toggle="tooltip" @click="choose($event)" title="window-close-o">
          <i class="fa fa-window-close-o"></i>
        </li>
        <li data-font="window-maximize" data-toggle="tooltip" @click="choose($event)" title="window-maximize">
          <i class="fa fa-window-maximize"></i>
        </li>
        <li data-font="window-minimize" data-toggle="tooltip" @click="choose($event)" title="window-minimize">
          <i class="fa fa-window-minimize"></i>
        </li>
        <li data-font="window-restore" data-toggle="tooltip" @click="choose($event)" title="window-restore">
          <i class="fa fa-window-restore"></i>
        </li>
        <li data-font="windows" data-toggle="tooltip" @click="choose($event)" title="windows">
          <i class="fa fa-windows"></i>
        </li>
        <li data-font="won" data-toggle="tooltip" @click="choose($event)" title="won">
          <i class="fa fa-won"></i>
        </li>
        <li data-font="wordpress" data-toggle="tooltip" @click="choose($event)" title="wordpress">
          <i class="fa fa-wordpress"></i>
        </li>
        <li data-font="wpbeginner" data-toggle="tooltip" @click="choose($event)" title="wpbeginner">
          <i class="fa fa-wpbeginner"></i>
        </li>
        <li data-font="wpexplorer" data-toggle="tooltip" @click="choose($event)" title="wpexplorer">
          <i class="fa fa-wpexplorer"></i>
        </li>
        <li data-font="wpforms" data-toggle="tooltip" @click="choose($event)" title="wpforms">
          <i class="fa fa-wpforms"></i>
        </li>
        <li data-font="wrench" data-toggle="tooltip" @click="choose($event)" title="wrench">
          <i class="fa fa-wrench"></i>
        </li>
        <li data-font="xing" data-toggle="tooltip" @click="choose($event)" title="xing">
          <i class="fa fa-xing"></i>
        </li>
        <li data-font="xing-square" data-toggle="tooltip" @click="choose($event)" title="xing-square">
          <i class="fa fa-xing-square"></i>
        </li>
        <li data-font="y-combinator" data-toggle="tooltip" @click="choose($event)" title="y-combinator">
          <i class="fa fa-y-combinator"></i>
        </li>
        <li data-font="y-combinator-square" data-toggle="tooltip" @click="choose($event)" title="y-combinator-square">
          <i class="fa fa-y-combinator-square"></i>
        </li>
        <li data-font="yahoo" data-toggle="tooltip" @click="choose($event)" title="yahoo">
          <i class="fa fa-yahoo"></i>
        </li>
        <li data-font="yc" data-toggle="tooltip" @click="choose($event)" title="yc">
          <i class="fa fa-yc"></i>
        </li>
        <li data-font="yc-square" data-toggle="tooltip" @click="choose($event)" title="yc-square">
          <i class="fa fa-yc-square"></i>
        </li>
        <li data-font="yelp" data-toggle="tooltip" @click="choose($event)" title="yelp">
          <i class="fa fa-yelp"></i>
        </li>
        <li data-font="yen" data-toggle="tooltip" @click="choose($event)" title="yen">
          <i class="fa fa-yen"></i>
        </li>
        <li data-font="yoast" data-toggle="tooltip" @click="choose($event)" title="yoast">
          <i class="fa fa-yoast"></i>
        </li>
        <li data-font="youtube" data-toggle="tooltip" @click="choose($event)" title="youtube">
          <i class="fa fa-youtube"></i>
        </li>
        <li data-font="youtube-play" data-toggle="tooltip" @click="choose($event)" title="youtube-play">
          <i class="fa fa-youtube-play"></i>
        </li>
        <li data-font="youtube-square" data-toggle="tooltip" @click="choose($event)" title="youtube-square">
          <i class="fa fa-youtube-square"></i>
        </li>
      </ul>
    </div>
  </section>
</template>

<script lang="ts">
  import Vue from "vue";
  import Component from "vue-class-component";
  import {Dependencies} from "~/core/decorator";
  import {Emit, Prop} from "vue-property-decorator";
  import {ModuledetailService} from "~/server/services/system-manage-services/moduleDetail.service";

  @Component({
    components: {}
  })

  export default class AddModule extends Vue {
    @Dependencies(ModuledetailService)
    private moduledetailService: ModuledetailService;

    @Emit("iconRefresh")
    iconRefresh(param) {
    }

    @Emit("close")
    close() {
    }

    choose(e) {
      let className: any = e.target.className;
      this.iconRefresh(className);
      this.close();
    }
  }

</script>

<style lang="less" scoped>
  .inpurClass,
  .el-input {
    width: 300px !important;
  }

  #chooseicon {
    margin: 10px;
  }

  #chooseicon ul {
    text-align: center;
    height: 500px;
    overflow: scroll;
    background: #F0F3F9;
    padding: 0px;

  }

  #chooseicon ul li {
    float: left;
    list-style: none;
    width: 40px;
    height: 40px;
    line-height: 40px;
    padding: 1px;
    margin: 1px;
    text-align: center;
    font-size: 18px;
  }
</style>
