mkdir assignment/dummy
cp -r assignment/public_html assignment/dummy
cp -r assignment/dummy/public_html assignment/public_html
rm -r assignment/dummy
mv assignment/public_html/public_html assignment/public_html/pub_html


