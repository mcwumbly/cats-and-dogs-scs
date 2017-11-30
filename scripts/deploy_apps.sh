pushd backend && cf push
popd
pushd frontend && cf push
popd
echo "" && echo "Done!" && echo ""
