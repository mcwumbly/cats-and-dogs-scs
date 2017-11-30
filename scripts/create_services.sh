echo -n "Creating Service Registry..."
{
  cf create-service p-service-registry standard service-registry
} &> /dev/null
until [ `cf service service-registry | grep -c "succeeded"` -eq 1  ]
do
  echo -n "."
done
echo
echo -n "Creating Circuit Breaker Dashboard..."
{
  cf create-service p-circuit-breaker-dashboard standard circuit-breaker
} &> /dev/null
until [ `cf service circuit-breaker | grep -c "succeeded"` -eq 1  ]
do
  echo -n "."
done
echo
echo "Service Registry created."
echo "" && echo "Done!" && echo ""
