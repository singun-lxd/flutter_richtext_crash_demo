import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'RichText Crash Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: Scaffold(
        backgroundColor: Colors.white,
        body: Center(
          child: RichText(
            text: TextSpan(children: [
              // remove [WidgetSpan] and crash not happen any more
              WidgetSpan(child: SizedBox.shrink()),
              TextSpan(
                text: "Text",
                style: TextStyle(color: Colors.black),
                // remove [recognizer] and crash not happen any more
                recognizer: TapGestureRecognizer()..onTap = () {},
              )
            ]),
          ),
        ),
      ),
    );
  }
}
